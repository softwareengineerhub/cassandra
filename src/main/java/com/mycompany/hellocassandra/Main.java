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
public class Main {

    public static void main(String[] args) {        
       
        String description = "Data asdasdas;10";
        int start = description.lastIndexOf(";");
        String str = description.substring(start+1);
        System.out.println(str);
        System.out.println(description.substring(0, start));
        
        /*DAO dao = new DAOImpl();
        Person person = new Person();
        person.setAge(30);
        person.setName("Denis");
        dao.create(person);
        person = dao.read("Denis");
        System.out.println(person);*/
        
        /*Person p1 = new Person();
        p1.setAge(45);
        p1.setName("Piter");
        person=dao.update("Denis", p1);
        System.out.println(person);
        dao.delete("Piter");*/
    }

}
