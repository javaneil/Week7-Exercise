package com.neil.persistence;

import com.neil.entity.Users;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 3/8/2017.
 */
public class UsersDao {

    private final Logger log = Logger.getLogger( this.getClass() ) ;

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>() ;
        Session session = SessionFactoryProvider.getSessionFactory().openSession() ;
        try {
            users = session.createCriteria(Users.class).list();
        }
        catch ( HibernateException hex ) {
            log.error("SessioncreateCriteria:  ", hex ) ;
        }
        finally {
            if ( null != session ) {
                session.close() ;
            }
        }
        return users ;
    }
}
