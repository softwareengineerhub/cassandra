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
public interface DAO {

    public void create(User user);

    public List<User> findAll();

    public User find(String login);

    public long count();

    public User update(String login, User candidateUser);

    public void remove(String login);

    public void createSchema();

    public void dropSchema();

    public void createTable();

    public void dropTable();
}
