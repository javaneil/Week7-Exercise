package com.neil.persistence;

import com.neil.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.* ;

/**
 * Created by Neil on 3/8/2017.
 */
public class UsersDaoTest {
    UsersDao dao ;

    @Before
    public void setup() {
        dao = new UsersDao() ;
    }


    /**
     * This is about the simplest test that can be done:
     * Create a single user
     * Verify added to table using Get-All
     * Delete same user
     * Verify removed using Get-All again...
     *
     * @throws Exception
     */
    @Test
    public void createUser() throws Exception {
        int startingSize = dao.getAllUsers().size() ;
        User user = new User( 0, "First", "Last", LocalDate.parse( "2000-01-01" ) ) ;
        int id = dao.createUser( user ) ;
        assertNotEquals( id, 0 ) ;
        assertTrue( dao.getAllUsers().size() == startingSize + 1 ) ; // 1 record added
        if ( 0 < id ) {
            dao.deleteUser( id ) ;
        }
        assertTrue( dao.getAllUsers().size() == startingSize ) ; // 1 record removed
    }
}

