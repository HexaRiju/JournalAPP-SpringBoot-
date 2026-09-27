package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Services.UsersServices;
import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicUser {
    @Autowired
    UsersServices userServices;


    @GetMapping("/health-check")
    public String healthCare() {
        return "ok";
    }

    @GetMapping
    public List<Users> getAll() {
        return userServices.getAllUsers();
    }

    @PostMapping("/create")
    public Users create(@RequestBody Users user){
        userServices.saveNewUser(user);
        return user;
    }


}
