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

        Session session = null ;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession() ;
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

        Session session = null ;
        Transaction transaction = null ;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession() ;
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
     * RETRIEVE - retrieve a user row given its id
     *
     * @param id the user's id
     * @return user
     */
    public User retrieveUser( int id ) {
        log.info( "UsersDao.retrieveUser( " + id + " )" ) ;
        User user = null ;

        Session session = null ;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession() ;
            user = (User) session.get( User.class, id ) ;
        }
        catch ( HibernateException hex ) {
            log.error( "Session.get fail:  ", hex ) ;
        }
        finally {
            if ( null != session ) {
                session.close();
            }
        }
        return user ;
    }


    /**
     * Delete - remove a user row by id
     * @param id the user's id
     *
     * REFERENCE: https://www.tutorialspoint.com/hibernate/hibernate_examples.htm
     */
    public void deleteUser(int id ) {
        log.info( "UserDao.deleteUser( " + id + " )" ) ;

        Session session = null ;
        Transaction transaction = null ;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession() ;
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
            if ( null != session ) {
                session.close();
            }
        }
    }


}
