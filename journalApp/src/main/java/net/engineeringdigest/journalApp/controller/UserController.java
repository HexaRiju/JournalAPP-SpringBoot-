package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Services.JournalEntityServices;
import net.engineeringdigest.journalApp.Services.UsersServices;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersServices userServices;

    @GetMapping
    public List<Users> getAll() {
        return userServices.getAllUsers();
    }

    @PostMapping
    public Users create(@RequestBody Users user) {
        userServices.saveEntry(user);
        return user;
    }

    @PutMapping("/{userName}")
    public Users update(@PathVariable String userName, @RequestBody Users user){
        Users userInDB = userServices.findByUsername(userName);
        if(userInDB != null){
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
            userServices.saveEntry(userInDB);
        }
        return userInDB;
    }

}
