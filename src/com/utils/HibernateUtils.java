package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    private static Configuration CONFIGURATION;
    private static SessionFactory SESSIONFACTORY;
    static {
        CONFIGURATION = new Configuration().configure();
        SESSIONFACTORY = CONFIGURATION.buildSessionFactory();
    }

    public static Session openSession(){
        return SESSIONFACTORY.openSession();
    }
}
