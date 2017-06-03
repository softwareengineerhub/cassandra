/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellocassandra.samples.goods.counter;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 *
 * @author denys
 * 
 * All Cassandra DataTypes http://docs.datastax.com/en/cql/3.1/cql/cql_reference/cql_data_types_c.html
 * 
 * 
 * 1. Error message while creating table:
 * "Cannot mix counter and non counter columns in the same table"
 * So, in 1 table we are not able to create counters and other columns
 * 
 * 2. Can not use INSERT when working with counters:
 * "INSERT statements are not allowed on counter tables, use UPDATE instead"
 * 
 * 3. Can not set value for counter - only increment\decrement 
 * UPDATE goods SET amount=0
 */
public class GoodsWithCounterExample {
    
    public static void main(String[] args){        
        Goods goods = new Goods("LG");
        goods.setAmount(1);
        insert(goods);
    }
    
    private static void insert(Goods goods){
        try(Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build()){
            Session session = cluster.connect("test");
            String query = "UPDATE goods SET amount=amount+%s WHERE name='%s'";
            String cql = String.format(query, goods.getAmount(), goods.getName());
            session.execute(cql);
        }
    }
    
}
