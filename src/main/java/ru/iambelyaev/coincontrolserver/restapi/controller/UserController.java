package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.Category;
import ru.iambelyaev.coincontrolserver.restapi.model.Operation;
import ru.iambelyaev.coincontrolserver.restapi.model.User;
import ru.iambelyaev.coincontrolserver.restapi.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService UserService;

    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }
    @PostMapping(value = "/user")
    public ResponseEntity<?> create(@RequestBody User User) {
        return UserService.create(User)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<String>("Name is null or password is null", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> read() {
        final List<User> User = UserService.readAll();
        return User != null && !User.isEmpty()
                ? new ResponseEntity<>(User, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<?> update(@RequestBody User User) {
        return UserService.update(User)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<String>("not found user_id", HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        return UserService.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<String>("not found user_id", HttpStatus.NOT_MODIFIED);
    }
}
