package com.neil.restservice;

import com.neil.entity.User;
import com.neil.persistence.UsersDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Neil on 3/8/2017.
 */

@Path( "/" )
public class Users {
    // The Java method will process HTTP GET requests
    @GET
    @Produces( MediaType.TEXT_HTML )
    public Response returnAll() {
        UsersDao dao = new UsersDao() ;
        List<User> users = dao.getAllUsers() ;
        String output = "<h2>Return All</h2><br />" + users.toString() ;
        return Response.status(200).entity(output).build() ;
    }

    @GET
    @Path( "/id/{param}" )
    @Produces( MediaType.TEXT_HTML )
    public Response returnUser( @PathParam( "param" ) String uid ) {
        String output = "<h1>Return by ID " + uid + "</h1>" ;
        return Response.status( 200).entity(output).build() ;
    }

}
