package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = uesrDaoService.fidnOne(id);

        if(user==null)
            throw  new UserNotFoundException("id:"+id);

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = uesrDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
