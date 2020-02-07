package com.java;

import com.model.User;
import com.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

public class HibernateTest01 {
    // 保存数据
    @Test
    public void demo1() {
        // hibernate加载核心配置文件
        Configuration configuration = new Configuration().configure();
        // 创建sessionFactory(连接池)
        SessionFactory factory = configuration.buildSessionFactory();
        // 获取session对象
        Session session = factory.openSession();
        // 开启事务，hibernate默认不自动提交事务
        Transaction transaction = session.beginTransaction();
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

    // 根据主键id查询数据
    @Test
    public void demo2() {
        // 加载配置文件
        Configuration configuration = new Configuration().configure();
        // 创建session工厂
        SessionFactory factory = configuration.buildSessionFactory();
        // 获取session
        Session session = factory.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        //逻辑操作
//        User u = session.get(User.class,2);
//        System.out.println(u.getName());

        User u = session.load(User.class, 3);
        System.out.println(u.getName());
        // 事务提交
        transaction.commit();
        // 关闭资源
        session.close();
        factory.close();
    }

    // 修改记录
    @Test
    public void demo3() {
        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

//        User user = new User();
//        user.setId(1);
//        user.setRemark("进行修改");
        User user = session.get(User.class, 2);
        user.setRemark("先查询再进行修改");

        session.update(user);

        transaction.commit();
        session.close();
        factory.close();
    }

    // 查询多条数据
    @Test
    public void demo4() {
        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
//        HQL
//        Query query = session.createQuery("from User ");
//        List<User> list = query.list();

//        Query query = session.createQuery("from User where id = ?0 and name = ?1");
//        query.setParameter(0, 2);
//        query.setParameter(1, "谷志雄");
//        query.setParameter(2,"谷志雄");
//        List<User> list = query.list();
//        QBC
//        Criteria criteria = session.createCriteria(User.class);
//        criteria.add(Restrictions.eq("id",2));
//        criteria.add(Restrictions.eq("name","谷志雄"));
//        List<User> list = criteria.list();
//        SQL
        SQLQuery query = session.createSQLQuery("select * from user");
//        SQLQuery query = session.createSQLQuery("select * from user where id = 2 and name = '谷志雄'");
        query.addEntity(User.class);
        List<User> list = query.list();


        for (User user : list) {
            System.out.println(user.getName());
        }

        transaction.commit();
        session.close();
        factory.close();
    }

    @Test
    public void demo5(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setName("测试c3p0！");

        session.save(user);
        transaction.commit();
        session.close();

    }
}
