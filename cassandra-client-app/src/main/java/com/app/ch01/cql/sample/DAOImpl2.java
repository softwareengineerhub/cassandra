/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch01.cql.sample;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denys.prokopiuk
 */
public class DAOImpl2 implements DAO {

    private static final String KEYSPACE_NAME = "my_keyspace";
    private Cluster cluster;

    public DAOImpl2() {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
    }

    @Override
    public void create(User user) {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            String cql = String.format("INSERT INTO users(login, content, role) VALUES('%s','%s','%s')", user.getLogin(), user.getContent(), user.getRole());
            session.execute(cql);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            ResultSet rs = session.execute("SELECT * FROM users");
            List<User> users = new ArrayList<>();
            for (Row row : rs) {
                String login = row.getString("login");
                String content = row.getString("content");
                String role = row.getString("role");
                User user = new User();
                user.setContent(content);
                user.setLogin(login);
                user.setRole(role);
                users.add(user);
            }
            return users;
        }
    }

    @Override
    public User find(String login) {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            ResultSet rs = session.execute("SELECT * FROM users");
            for (Row row : rs) {
                String content = row.getString("content");
                String role = row.getString("role");
                User user = new User();
                user.setContent(content);
                user.setLogin(login);
                user.setRole(role);
                return user;
            }
            return null;
        }
    }

    @Override
    public long count() {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            ResultSet rs = session.execute("SELECT count(*) FROM users");
            for (Row row : rs) {
                return row.getLong(0);
            }
            return 0;
        }
    }

    @Override
    public User update(String login, User candidateUser) {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            String cql = String.format("UPDATE users SET content='%s', role='%s' WHERE login='%s'", candidateUser.getContent(), candidateUser.getRole(), login);
            session.execute(cql);
        }
        return candidateUser;
    }

    @Override
    public void remove(String login) {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            String cql = String.format("DELETE FROM users WHERE login='%s'", login);
            session.execute(cql);
        }
    }

    @Override
    public void createSchema() {
        try (Session session = cluster.connect()) {
            String cql = "CREATE KEYSPACE IF NOT EXISTS  my_keyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1  }";
            session.execute(cql);
        }
    }

    @Override
    public void dropSchema() {
       try (Session session = cluster.connect()) {
            String cql = "DROP KEYSPACE IF EXISTS my_keyspace";
            session.execute(cql);
        }
       cluster.close();
    }

    @Override
    public void createTable() {
       try (Session session = cluster.connect(KEYSPACE_NAME)) {
            String cql = "CREATE TABLE IF NOT EXISTS users(login text, content text, role text,  PRIMARY KEY (login))";
            session.execute(cql);
        }
    }

    @Override
    public void dropTable() {
        try (Session session = cluster.connect(KEYSPACE_NAME)) {
            String cql = "DROP TABLE IF EXISTS users";
            session.execute(cql);
        }
        
    }

}
