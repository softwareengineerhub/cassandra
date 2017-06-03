/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellocassandra;

//import org.apache.commons.math3.ml.clustering.Cluster;

import com.datastax.driver.core.*;

/**
 *
 * @author denys
 */
public class DAOImpl implements DAO {

    @Override
    public void create(Person person) {
        try (Cluster cluster = getCluster()) {
            Session session = cluster.connect("persons");
            session.execute("INSERT into person(p_id, name, age) VALUES(uuid(),'"+person.getName()+"',"+person.getAge()+")");
        }
    }

    @Override
    public Person read(String name) {
        try (Cluster cluster = getCluster()) {
            Session session = cluster.connect("persons");
            ResultSet rs=session.execute("SELECT * FROM person WHERE name='"+name+"')");
            Person person = new Person();
            person.setName(name);
            for(Row row: rs){
                int age = row.getInt("age");
                person.setAge(age);
            }
            return person;
        }
    }

    @Override
    public Person update(String name, Person person) {
        try(Cluster cluster = getCluster()){
            Session session = cluster.connect("persons");
            ResultSet rs=session.execute("SELECT * FROM person WHERE name='"+name+"')");
            Person oldPerson = new Person();
            oldPerson.setName(name);
            for(Row row: rs){
                int age = row.getInt("age");
                oldPerson.setAge(age);
            }
            session.execute("UPDATE person SET age="+oldPerson.getAge()+" WHERE name='"+name+"'");
            return oldPerson;
        }
    }

    @Override
    public void delete(String name) {
        Cluster cluster = getCluster();
        Session session = cluster.connect("persons");
        session.execute("INSERT into person()");
    }
   
    private Cluster getCluster(){
        return Cluster.builder().addContactPoint("127.0.0.1").build();
    }
    
}
