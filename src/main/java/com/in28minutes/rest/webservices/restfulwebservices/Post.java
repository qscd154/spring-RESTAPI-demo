package com.in28minutes.rest.webservices.restfulwebservices;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }



}
