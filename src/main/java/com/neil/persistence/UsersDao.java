package com.neil.persistence;

import com.neil.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 3/8/2017.
 */
public class UsersDao {

    private final Logger log = Logger.getLogger( this.getClass() ) ;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>() ;
        Session session = SessionFactoryProvider.getSessionFactory().openSession() ;
        try {
            users = session.createCriteria(User.class).list();
        }
        catch ( HibernateException hex ) {
            log.error("Session.createCriteria:  ", hex ) ;
        }
        finally {
            if ( null != session ) {
                session.close() ;
            }
        }
        return users ;
    }

    /**
     * CREATE - add a new user row
     *
     * @param user
     * @return the id of the inserted record
     *
     * REFERENCE: https://www.tutorialspoint.com/hibernate/hibernate_examples.htm
     */
    public int createUser( User user ) {
        log.info( "UserDao.createUser( " + user + " )" ) ;
        int id = 0 ;

        Session session = SessionFactoryProvider.getSessionFactory().openSession() ;
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction() ;
            id = (int) session.save( user ) ;
            transaction.commit() ;
        }
        catch ( HibernateException hex ) {
            log.error( "session.save fail:  ", hex ) ;
            if ( null != transaction ) {
                transaction.rollback() ;
            }
        }
        finally {
            if ( null != session ) {
                session.close();
            }
        }
        return id ;
    }


    /**
     * Delete - remove a user row by id
     * @param id the user's id
     *
     * REFERENCE: https://www.tutorialspoint.com/hibernate/hibernate_examples.htm
     */
    public void deleteUser(int id ) {
        log.info( "UserDao.deleteUser( " + id + " )" ) ;

        Session session = SessionFactoryProvider.getSessionFactory().openSession() ;
        Transaction transaction = null ;
        try {
            transaction = session.beginTransaction() ;
            User user = (User) session.get( User.class, id ) ;
            session.delete( user ) ;
            transaction.commit() ;
        }
        catch ( HibernateException hex ) {
            log.error( "session.delete fail:  ", hex ) ;
            if ( null != transaction ) {
                transaction.rollback() ;
            }
        }
        finally {
            session.close() ;
        }
    }


}
