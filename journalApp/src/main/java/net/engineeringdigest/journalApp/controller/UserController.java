package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Services.UsersServices;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersServices userServices;
    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public Users update(@RequestBody Users user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userInDB = userServices.findByUsername(username);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        userServices.saveUser(userInDB);
        return userInDB;
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
