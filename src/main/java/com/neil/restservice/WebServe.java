package com.neil.restservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Neil on 3/8/2017.
 */
//Defines the base URI for all resource URIs.
@ApplicationPath( "/users" )

//The java class declares root resource and provider classes
public class WebServe extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet services = new HashSet<Class<?>>();
        services.add(Users.class);

        // Additional classes here...

        return services;
    }

}