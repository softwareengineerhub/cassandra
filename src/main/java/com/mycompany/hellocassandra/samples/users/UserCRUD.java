/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellocassandra.samples.users;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denys
 *
 * UPSET - inserts when update by not existing PK, update when insert by
 * existing PK CAN NOT UPDATE PK ---> "PRIMARY KEY part name found in SET part"
 * remove by not existing key - no exceptions
 *
 */
public class UserCRUD {

    public static void main(String[] args) {
        //User user = new User("Denis","111111","role1","my.mail1");
        //insert(user);
        
        
        User currentUser = new User("Denis1", "111111", "role1", "my.mail1");
        User user = new User("DenisProkopiuk", "11111", "roleD", "denis4321@gamile.com");        
        //update(currentUser, user);
        
        /*List<User> users = select();
        for(User u: users){
            System.out.println(u);
        }        
        User u = select(currentUser);
        System.out.println(u);*/
        
        //delete(currentUser);
    }

    private static void insert(User user) {
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            String query = "INSERT INTO users(name, password, role, email) VALUES('%s','%s','%s','%s')";
            String cql = String.format(query, user.getName(), user.getPassword(), user.getRole(), user.getEmail());
            session.execute(cql);
        }
    }

    private static void update(User currentUser, User user) {
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            //String query = "UPDATE users SET name='%s', password='%s', role='%s', email='%s' WHERE name='%s'";
            //String cql = String.format(query, user.getName(), user.getPassword(), user.getRole(), user.getEmail(), currentUser.getName());
            String query = "UPDATE users SET password='%s', role='%s', email='%s' WHERE name='%s'";
            String cql = String.format(query, user.getPassword(), user.getRole(), user.getEmail(), currentUser.getName());
            session.execute(cql);
        }
    }

    private static User select(User user) {
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            ResultSet rs = session.execute("SELECT * FROM users WHERE name='" + user.getName() + "'");
            for (Row row : rs) {
                String name = row.getString("name");
                String password = row.getString("password");
                String role = row.getString("role");
                String email = row.getString("email");
                return new User(name, password, role, email);
            }
        }
        return null;
    }

    private static List<User> select() {
        List<User> users = new ArrayList();
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            ResultSet rs = session.execute("SELECT * FROM users");
            for (Row row : rs) {
                String name = row.getString("name");
                String password = row.getString("password");
                String role = row.getString("role");
                String email = row.getString("email");
                users.add(new User(name, password, role, email));
            }
        }
        return users;
    }
    
    private static void delete(User user) {        
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            session.execute("DELETE FROM users WHERE name='"+user.getName()+"'");
        }        
    }

}
