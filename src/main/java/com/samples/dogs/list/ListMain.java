/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samples.dogs.list;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author denys
 * 
 * LIST - uses [] for update
 * SET - uses {} for update
 * 
 */
public class ListMain {

    public static void main(String[] args) {
        updateDog();
        //updateDog1();
        //updateDog2();
        //updateDog3();
    }

    private static void insertDog() {
        Dog dog = initDog(4, "Tosha");
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            String query = "INSERT INTO dogs(name, age, friends) VALUES('%s',%s,%s)";            
            String cql = String.format(query, dog.getName(), dog.getAge(), listToString(dog.getFriends()));            
            session.execute(cql);
        }
    }
    
    private static void updateDog1() {        
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");     
            String cql = "UPDATE dogs SET friends[0] = 'denis' WHERE name='Tosha'";            
            session.execute(cql);
        }
    }
    
    private static void updateDog2() {
        Dog dog = initDog(4, "Tosha");
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            //String query = "UPDATE dogs SET friends = friends+%s WHERE name='%s'";
            //String cql = String.format(query, "{'denis'}", dog.getName());
            String cql = "UPDATE dogs SET friends = friends + ['Lena']  WHERE name='Tosha'";
            //String cql = String.format(query, "{'denis'}", dog.getName());
            session.execute(cql);
        }
    }
    
    private static void updateDog3() {
        Dog dog = initDog(4, "Tosha");
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");            
            String cql = "UPDATE dogs SET friends = ['Jenia'] + friends  WHERE name='Tosha'";            
            session.execute(cql);
        }
    }
    
    private static void updateDog() {
        Dog dog = initDog(4, "Tosha");
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            String query = "UPDATE dogs SET friends = friends+%s WHERE name='%s'";
            String cql = String.format(query, "['natasha','dima']", dog.getName());            
            session.execute(cql);
        }
    }
    
    private static String listToString(List list){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Object obj: list){
            sb.append("'");
            sb.append(obj);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    private static Dog initDog(int age, String name) {
        Dog dog = new Dog();
        dog.setAge(age);
        dog.setName(name);
        dog.setFriends(Arrays.asList("A", "B", "C"));
        return dog;
    }

}
