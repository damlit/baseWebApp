package com.currency.currencyservice.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static HibernateFactory instance;

    private HibernateFactory(){
    }

    public static HibernateFactory getInstance() {
        if (instance == null) {
            instance = new HibernateFactory();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(registryBuilder.build());

        return sessionFactory;
    }
}
