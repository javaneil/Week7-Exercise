package com.neil.entity;

import com.neil.util.LocalDateAttributeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Neil on 3/8/2017.
 */

@Entity
@Table( name = "users" )
public class Users {

    @Id
    @GeneratedValue( generator = "increment" )
    @GenericGenerator( name = "increment", strategy = "increment" )
    @Column( name = "id" )
    private int id ;

    @Column( name = "first_name" )
    private String first_name ;

    @Column( name = "last_name" )
    private String last_name ;

    @Column( name = "date_of_birth" )
    @Convert( converter = LocalDateAttributeConverter.class )
    private LocalDate date_of_birth ;

    public Users() {
    }

    public Users( int id, String first, String last, LocalDate bday ) {
        this.id = id ;
        this.first_name = first ;
        this.last_name = last ;
        this.date_of_birth = bday ;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

}
