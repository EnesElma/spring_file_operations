package com.enes.user.controller;

import com.enes.user.entity.User;
import com.enes.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("create")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findUser(@PathVariable long id){
        return ResponseEntity.ok(userService.findUser(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User delete success",HttpStatus.ACCEPTED);
    }

}
