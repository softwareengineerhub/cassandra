/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellocassandra.samples.users;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ColumnDefinitions.Definition;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

/**
 *
 * @author denys
 */
public class MetaData {

    public static void main(String[] args) {
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();) {
            Session session = cluster.connect("test");
            ResultSet rs = session.execute("SELECT * FROM users");
            ColumnDefinitions columnDefinitions = rs.getColumnDefinitions();
            for (Definition definition : columnDefinitions.asList()) {
                System.out.println("########################");
                String keyspace = definition.getKeyspace();
                String name = definition.getName();
                String table = definition.getTable();
                DataType dataType = definition.getType();
                System.out.println("keyspace=" + keyspace);
                System.out.println("name=" + name);
                System.out.println("table=" + table);
                System.out.println("dataType=" + dataType);
            }
        }
    }

}
