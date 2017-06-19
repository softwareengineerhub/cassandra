/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samples.ducks.map;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author denys
 */
public class MapMain {

    public static void main(String[] args) {
       // insertDuck();
       updateMap();
    }

    private static void insertDuck() {
        Duck duck = initDuck();
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            String query = "INSERT INTO ducks(name, age, data) VALUES('%s',%s,%s)";
            String dataStr=mapString(duck.getData());            
            String cql = String.format(query, duck.getName(), duck.getAge(), dataStr);
            session.execute(cql);
        }
    }

    private static void updateMap() {
        Duck duck = initDuck();
        try (Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()) {
            Session session = cluster.connect("test");
            String query = "UPDATE ducks SET data['A']=30 WHERE name='%s'";            
            String cql = String.format(query, duck.getName());
            session.execute(cql);
        }
    }
    
    private static String mapString(Map<String, Integer> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(String key: data.keySet()){
            sb.append("'");
            sb.append(key);
            sb.append("':");
            sb.append(data.get(key));
            sb.append(",");
        }        
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }

    private static Duck initDuck() {
        Duck duck = new Duck();
        duck.setAge(10);
        duck.setName("Donald");
        Map<String, Integer> map = new HashMap();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        duck.setData(map);
        return duck;
    }

}
