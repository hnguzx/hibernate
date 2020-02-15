package com.java;

import com.model.Customer;
import com.model.Order;
import com.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class OnToManyTest {
    @Test
    public void demo1() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("谷志雄");

        Order order1 = new Order();
        order1.setAddr("湖南");
        Order order2 = new Order();
        order2.setAddr("广东");

        order1.setCustomer(customer);
        order2.setCustomer(customer);

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        session.save(customer);
        session.save(order1);
        session.save(order2);

        transaction.commit();
        session.close();
    }

    @Test
//    级联保存
    public void demo2() {

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("梁红");

        Order order1 = new Order();
        order1.setAddr("衡阳");
        Order order2 = new Order();
        order2.setAddr("常德");

        order1.setCustomer(customer);
        order2.setCustomer(customer);

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        session.save(customer);
//        session.save(order1);

        transaction.commit();
        session.close();
    }

    @Test
//    级联删除
    public void demo3() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 1);
        session.delete(customer);

        transaction.commit();
        session.close();

    }
}
