package com.java;

import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest01 {
    @Test
    public void demo1(){
        // hibernate加载核心配置文件
        Configuration configuration = new Configuration().configure();
        // 创建sessionFactory(连接池)
        SessionFactory factory = configuration.buildSessionFactory();
        // 获取session对象
        Session session = factory.openSession();
        // 开启事务，hibernate默认不自动提交事务
        Transaction transaction= session.beginTransaction();
        // 业务逻辑操作
        User user = new User();
        user.setName("谷志雄");
        user.setAge(23);
        user.setRemark("这是一条备注！");

        session.save(user);
        // 事务提交
        transaction.commit();
        // 释放资源
        session.close();
        factory.close();

    }
}
