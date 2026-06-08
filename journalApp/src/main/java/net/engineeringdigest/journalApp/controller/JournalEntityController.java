package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Services.JournalEntityServices;
import net.engineeringdigest.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {

    @Autowired
    private JournalEntityServices journalEntityServices;

    @GetMapping
    public List<JournalEntity> getJournal() {
        return journalEntityServices.getAllJournal();
    }

    @PostMapping
    public JournalEntity enterData(@RequestBody JournalEntity journalEntity) {
        journalEntity.setDate(LocalDateTime.now());// using the date can be added "LocalDateTime.now()" as we do not giving date
        journalEntityServices.saveEntry(journalEntity);
        return journalEntity;
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getJournal(@PathVariable ObjectId myId) {
        return journalEntityServices.getJournalById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteJournal(@PathVariable ObjectId myId) {
        journalEntityServices.deleteJournal(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntity editJournal(@RequestBody JournalEntity journalEntity, @PathVariable ObjectId myId) {
        JournalEntity old = journalEntityServices.getJournalById(myId).orElse(null);
        if (old != null) {
            old.setTitle(journalEntity.getTitle() != null && !journalEntity.getTitle().isEmpty() ? journalEntity.getTitle() : old.getTitle());
            old.setContain(journalEntity.getContain() != null && !journalEntity.getContain().isEmpty() ? journalEntity.getContain() : old.getContain());
        }
        journalEntityServices.saveEntry(old);
        return old;
    }
}
