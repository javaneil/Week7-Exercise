package com.neil.persistence;

import com.neil.entity.Users;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void getAllUsers() throws Exception {
        List<Users> users = dao.getAllUsers() ;
        assertTrue( users.size() > 0 ) ;

    }
}

