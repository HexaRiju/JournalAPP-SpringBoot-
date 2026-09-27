package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Services.JournalEntityServices;
import net.engineeringdigest.journalApp.Services.UsersServices;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {

    @Autowired
    private JournalEntityServices journalEntityServices;
    @Autowired
    private UsersServices userServices;

    @GetMapping
    public ResponseEntity<?> getJournal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<JournalEntity> list = userServices.findByUsername(username).getJournalEntityList();
        if (list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> enterData(@RequestBody JournalEntity journalEntity) {// ? wild card mean we can return not only JournalEntity
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntity.setDate(LocalDateTime.now());// using the date can be added "LocalDateTime.now()" as we do not giving date
            journalEntityServices.saveEntry(journalEntity, username);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournal(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userServices.findByUsername(username);
        if (user.getJournalEntityList().stream().noneMatch(j -> j.getId().equals(myId)))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        Optional<JournalEntity> journalEntity = journalEntityServices.getJournalById(myId);
        if (journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            journalEntityServices.deleteJournal(myId, userName);
            return new ResponseEntity<>(myId, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> editJournal(@RequestBody JournalEntity journalEntity, @PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users user = userServices.findByUsername(userName);
        if(user.getJournalEntityList().stream().anyMatch(j -> j.getId().equals(myId))) {
            JournalEntity old = journalEntityServices.getJournalById(myId).orElse(null);
            if (old != null) {
                old.setTitle(journalEntity.getTitle() != null && !journalEntity.getTitle().isEmpty() ? journalEntity.getTitle() : old.getTitle());
                old.setContain(journalEntity.getContain() != null && !journalEntity.getContain().isEmpty() ? journalEntity.getContain() : old.getContain());
                journalEntityServices.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
