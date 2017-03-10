package com.neil.restservice;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.neil.entity.User;
import com.neil.persistence.UsersDao;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 3/8/2017.
 */

@Path( "/" )
@Produces( {MediaType.TEXT_HTML, MediaType.APPLICATION_JSON} )
public class Users {

    private final Logger log = Logger.getLogger( this.getClass() ) ;

    @GET
    @Path( "/html" )
    @Produces( MediaType.TEXT_HTML )
    public Response returnAllHtml() {
        UsersDao dao = new UsersDao() ;
        String output = "<html><h2>Returning All</h2>" ;
        List<User> users = dao.getAllUsers() ;

        for ( User usr : users ) {
            output += convertToHtmlText( usr ) + "<br />" ;
        }
        output += "</html>" ;
        return Response.status(200).entity(output).build() ;
    }

    @GET
    @Path( "/html/id={param}" )
    @Produces( MediaType.TEXT_HTML )
    public Response returnUser( @PathParam( "param" ) String uid ) {
        UsersDao dao = new UsersDao() ;
        String output = "<html><h1>Return by ID</h1>" ;

        User usr = dao.retrieveUser( Integer.valueOf( uid ) ) ;
        output += convertToHtmlText( usr ) ;
        output += "</html>" ;
        return Response.status( 200).entity(output).build() ;
    }


    @GET
    @Path( "/json" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response returnAllJson() {
        UsersDao dao = new UsersDao() ;
        String output = "" ;
        List<User> users = dao.getAllUsers() ;
        output = convertToJson( users ) ;
        return Response.status(200).entity(output).build() ;
    }

    @GET
    @Path( "/json/{param}" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response returnUserJson( @PathParam( "param" ) String uid ) {
        UsersDao dao = new UsersDao() ;
        List<User> users = new ArrayList<>() ;
        String output = "" ;

        users.add( dao.retrieveUser( Integer.valueOf( uid ) ) ) ;
        output = convertToJson( users ) ;
        return Response.status( 200).entity(output).build() ;
    }


    /**
     * Format raw database data into HTML
     * @param usr - User entity row
     * @return String suitable for HTML
     */
    private String convertToHtmlText( User usr ) {
        String retStr = new String() ;
        if ( null != usr ) {
            retStr += "  " + String.valueOf(usr.getId()) ;
            retStr += "  " + usr.getFirst_name() ;
            retStr += "  " + usr.getLast_name() ;
            retStr += "  " + usr.getDate_of_birth() ;
        }
        else {
            retStr += "User ID not found" ;
        }
        return retStr ;
    }


    private String convertToJson( List<User> list ) {
        ObjectMapper mapper = new ObjectMapper() ;
        String json = "" ;

        // found this on-line, it "prettys" the JSON output with CR/LFs
        mapper.configure( SerializationFeature.INDENT_OUTPUT, true ) ;

        try {
            json = mapper.writeValueAsString( list ) ;
        }
        catch ( JsonGenerationException e ) {
            log.error( "Jackson JsonGenerationException:  " ,e ) ;
        }
        catch ( JsonMappingException e ) {
            log.error ( "JsonMappingException:  ", e ) ;
        }
        catch ( IOException e ) {
            log.error( "IOException:  ", e ) ;
        }
        return json ;
    }

}


