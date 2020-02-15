package com.model;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Integer cid;
    private String name;
    private Set<Order> orders = new HashSet<>();

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

//    @Override
//    public String toString() {
//        return "Customer{" +
//                "cid=" + cid +
//                ", name='" + name +
//                '}';
//    }


    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
