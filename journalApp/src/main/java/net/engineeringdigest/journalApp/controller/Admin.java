package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Services.UsersServices;
import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class Admin {
    @Autowired
    UsersServices userServices;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<Users> users = userServices.getAllUsers();
        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody Users user) {
        try {
            userServices.saveNewAdmin(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
