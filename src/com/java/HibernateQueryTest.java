package com.java;

import com.model.Customer;
import com.model.Order;
import com.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HibernateQueryTest {
    @Test
//    单表查询
    public void demo1() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

//        查询所有
//        Query query = session.createQuery("From Customer ");
//        List<Customer> list = query.list();
//        for (Customer customer : list) {
//            System.out.println(customer);
//        }
//        使用别名
//        Query query = session.createQuery("From Customer as c ");
//        List<Customer> list = query.list();
//        带参数查询
//        Query query = session.createQuery("From Customer c where c.name = ?1 and c.id = ?2");
//        query.setParameter(1,"谷志雄");
//        query.setParameter(2,2);
//        List<Customer> list = query.list();
//        排序
//        Query query = session.createQuery("From Customer as c order by c.id asc ");
//        List<Customer> list = query.list();
//        分页查询
//        Query query = session.createQuery("From Order");
//        query.setFirstResult(0);
//        query.setMaxResults(2);
//        List<Order> list = query.list();
//        单个对象查询
//        Query query = session.createQuery("From Order o where o.addr = ?1");
//        Order order = (Order) query.setParameter(1,"衡阳").uniqueResult();
//        System.out.println(order);
//        投影查询
//        Query query = session.createQuery("select o.addr From Order o");
//        List<Object> list = query.list();
//        for (Object order : list) {
//            System.out.println(order);
//        }
//        模糊查询
        Query query = session.createQuery("From Order o where o.addr like ?1");
        query.setParameter(1, "衡%");


        List<Order> list = query.list();
        for (Order order : list) {
            System.out.println(order);
        }

        transaction.commit();
        session.close();
    }

    @Test
//    多表查询
    public void demo2() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        交叉连接
//        Query query = session.createQuery("from Customer c join c.orders");
//        List<Object[]> list = query.list();
//        for (Object[] object : list) {
//            System.out.println(Arrays.toString(object));
//        }
//        内连接
//        Query query = session.createQuery("from Customer c inner join c.orders");
//        List<Object[]> list = query.list();
//        for (Object[] object : list) {
//            System.out.println(Arrays.toString(object));
//        }
//        迫切内连接
        Query query = session.createQuery("select distinct c from Customer c inner join fetch c.orders");
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        transaction.commit();
        session.close();


    }

    @Test
//    聚集函数
    public void demo3() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

//        Query query = session.createQuery("select count (*) from Customer ");
//        Long o = (Long) query.uniqueResult();

        Query query = session.createQuery("select sum (c.id) from Customer c");
        Long o = (Long) query.uniqueResult();
        System.out.println(o);

        transaction.commit();
        session.close();
    }

    @Test
//    离线条件查询
    public void demo4() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.eq("id", 2));

        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria1 = criteria.getExecutableCriteria(session);
        List<Customer> list = criteria1.list();

        for (Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        session.close();
    }

}
