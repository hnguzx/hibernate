package com.java;

import com.model.User;
import com.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateTest02 {

    @Test
    public void demo1() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setName("缓存1");
        user.setRemark("11备注");
        user.setAge(23);

        session.save(user);

        User user1 = session.get(User.class, user.getId());

        System.out.println(user1.getName());
        transaction.commit();
        session.close();
    }

    @Test
    public void demo2() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User user1 = session.get(User.class, 7);
        User user3 = session.get(User.class, 8);

        System.out.println("1111111" + user1.getName());
//        session.clear();
        session.evict(user3);
        User user2 = session.get(User.class, 7);
        User user4 = session.get(User.class, 8);

        System.out.println("222222222" + user2.getName());
        transaction.commit();
        session.close();
    }
}
