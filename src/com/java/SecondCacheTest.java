package com.java;

import com.model.Customer;
import com.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class SecondCacheTest {
    @Test
    public void demo(){
        Session session = HibernateUtils.openSession();
        Transaction tx = session.beginTransaction();

        Customer customer1 = (Customer) session.get(Customer.class, 1);// 发送SQL.
        Customer customer2 = (Customer) session.get(Customer.class, 1);// 不发送SQL.

        tx.commit();

        session = HibernateUtils.getCurrentSession();
        tx = session.beginTransaction();

        Customer customer3 = (Customer) session.get(Customer.class, 1);// 发送SQL.

        tx.commit();
    }
}
