package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = uesrDaoService.fidnOne(id);

        if(user==null)
            throw  new UserNotFoundException("id:"+id);
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    //GET /users
    public void deleteUser(@PathVariable int id) {
        uesrDaoService.deleteById(id);
    }



    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = uesrDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
