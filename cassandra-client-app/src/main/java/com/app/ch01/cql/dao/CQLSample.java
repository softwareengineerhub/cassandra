/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch01.cql.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author denys.prokopiuk
 */
public class CQLSample {

    public static void main(String[] args) {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.connect("my_keyspace");

        System.out.println("----------------------INSERT----------------------");
        session.execute("INSERT INTO users(login, content, role) VALUES('user01','data','app-user')");

        System.out.println("----------------------SELECT *----------------------");
        ResultSet rs = session.execute("SELECT * FROM users");
        for (Row row : rs) {
            String login = row.getString("login");
            String content = row.getString("content");
            String role = row.getString("role");
            System.out.println(String.format("%s; %s; %s", login, content, role));
        }

        System.out.println("----------------------SELECT * WHERE----------------------");
        rs = session.execute("SELECT * FROM users WHERE login='user01'");
        for (Row row : rs) {
            String login = row.getString("login");
            String content = row.getString("content");
            String role = row.getString("role");
            System.out.println(String.format("%s; %s; %s", login, content, role));
        }

        System.out.println("----------------------UPDATE----------------------");
        session.execute("UPDATE users SET content = 'my-data' WHERE login='user01'");

        System.out.println("----------------------SELECT * WHERE----------------------");
        rs = session.execute("SELECT * FROM users WHERE login='user01'");
        for (Row row : rs) {
            String login = row.getString("login");
            String content = row.getString("content");
            String role = row.getString("role");
            System.out.println(String.format("%s; %s; %s", login, content, role));
        }

        System.out.println("----------------------REMOVE----------------------");
        session.execute("DELETE FROM users WHERE login='user01'");

        System.out.println("----------------------SELECT count(*)----------------------");
        rs = session.execute("SELECT count(*) FROM users");
        for (Row row : rs) {
            long count = row.getLong(0);
            System.out.println(String.format("count=%s", count));
        }

        session.close();
        cluster.close();

       /* try (Cluster cluster1 = Cluster.builder().addContactPoint("127.0.0.1").build();
                Session session1 = cluster.connect("my_keyspace");) {

        }*/

    }

}
