/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samples.goods.counter;

/**
 *
 * @author denys
 */
public class Goods {

    private String name;
    private int amount;

    public Goods() {
    }

    public Goods(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Goods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
