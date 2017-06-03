/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellocassandra;

/**
 *
 * @author denys
 */
public interface DAO {
    
    public void create(Person person);
    
    public Person read(String name);
    
    public Person update(String name, Person person);
    
    public void delete(String name);    
}
