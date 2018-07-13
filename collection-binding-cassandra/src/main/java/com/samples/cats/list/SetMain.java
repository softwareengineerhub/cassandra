/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samples.cats.list;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author denys
 */
public class SetMain {

    public static void main(String[] args) {
        insertCat();
    }

    private static void insertCat() {
        Cat cat = initCat();
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            String query = "INSERT INTO cats(name, age, friends) VALUES('%s',%s,%s)";
            String cql = String.format(query, cat.getName(), cat.getAge(), stringSet(cat.getFriends()));
            session.execute(cql);
        }
    }

    private static Cat initCat() {
        Cat cat = new Cat();
        cat.setAge(10);
        cat.setName("Cat1");
        Set set = new HashSet();
        set.add("A");
        set.add("B");
        set.add("C");
        cat.setFriends(set);
        return cat;
    }
    
    private static String stringSet(Set set) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Object obj : set) {
            sb.append("'");
            sb.append(obj);
            sb.append("'");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

}
