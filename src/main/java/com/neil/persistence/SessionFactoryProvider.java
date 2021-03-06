package com.neil.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This file provides a SessionFactory for use with DAOS using Hibernate
 * Created by Neil on 3/8/2017.
 */
public class SessionFactoryProvider {
    private static SessionFactory sessionFactory ;

    public static void createSessionFactory() {

        Configuration configuration = new Configuration() ;
        configuration.configure() ;
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings( configuration.getProperties() ).build();

        sessionFactory = configuration.buildSessionFactory( serviceRegistry ) ;
    }

    public static SessionFactory getSessionFactory() {
        if ( sessionFactory == null ) {
            createSessionFactory() ;
        }
        return sessionFactory ;
    }

}
