/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ch01.cql.sample;

import java.util.List;

/**
 *
 * @author denys.prokopiuk
 */
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //DAO dao = new DAOImpl();
        //DAO dao = new DAOImpl2();
        DAO dao = new DAOImpl3();
        dao.createSchema();
        dao.createTable();        
        System.out.println("-----Call start-----------" + start);
        long count = dao.count();
        System.out.println("count=" + count);
        System.out.println("-----Call finish-----------" + (System.currentTimeMillis() - start));

        System.out.println("-----------Insert 1 user------------------");
        User user = new User();
        user.setLogin("userA");
        user.setContent("contentA");
        user.setRole("app-user");
        dao.create(user);
        System.out.println("-----------------------------");
        System.out.println("-----------Count------------------");
        count = dao.count();
        System.out.println("count=" + count);
        System.out.println("-----------------------------");
        System.out.println("-----------Insert Bulk users------------------");
        for (int i = 0; i < 10; i++) {
            User u = new User();
            u.setContent("content" + i);
            u.setLogin("user" + i);
            u.setRole("role" + i);
            dao.create(u);
        }
        System.out.println("-----------------------------");
        System.out.println("-----------Count------------------");
        count = dao.count();
        System.out.println("count=" + count);
        System.out.println("-----------------------------");
        System.out.println("--------FIND_ALL---------------------");
        List<User> users = dao.findAll();
        for(User u: users){
            System.out.println(u);
        }
        System.out.println("-----------------------------");
        System.out.println("--------FIND BY LOGIN---------------------");
        User u = dao.find("userA");        
        System.out.println(u);        
        System.out.println("-----------------------------");
        System.out.println("--------UPDATE---------------------");
        User updateUser = new User();
        updateUser.setRole("admin-role");
        updateUser.setContent("contentB");
        dao.update("userA", updateUser);
        System.out.println("-----------------------------");
        System.out.println("--------FIND BY LOGIN---------------------");
        u = dao.find("userA");        
        System.out.println(u);        
        System.out.println("-----------------------------");
        System.out.println("-----------Count------------------");
        count = dao.count();
        System.out.println("count=" + count);
        System.out.println("-----------------------------");        
        System.out.println("-------------REMOVE----------------");
        dao.remove("userA");
        System.out.println("-----------------------------");
        System.out.println("-----------Count------------------");
        count = dao.count();
        System.out.println("count=" + count);
        System.out.println("-----------------------------");
        dao.dropTable();
        dao.dropSchema();
        System.out.println("-------------TOTAL:--------------"+(System.currentTimeMillis()-start));
        
    }

}
