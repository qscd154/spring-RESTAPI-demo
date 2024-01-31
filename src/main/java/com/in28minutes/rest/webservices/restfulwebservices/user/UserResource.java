package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    private UesrDaoService uesrDaoService;


    public UserResource(UesrDaoService uesrDaoService) {
        this.uesrDaoService = uesrDaoService;
    }




    @GetMapping("/users")
    //GET /users
    public List<User> retrieveAllUsers() {
        return uesrDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    //GET /users
    public User retrieveUser(@PathVariable int id) {
        return uesrDaoService.fidnOne(id);
    }


}
