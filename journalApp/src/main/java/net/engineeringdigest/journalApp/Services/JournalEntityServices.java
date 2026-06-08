package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.repository.JavaEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntityServices {
    @Autowired
    private JavaEntityRepository javaEntityRepository;

    public void saveEntry(JournalEntity journalEntity) {
        javaEntityRepository.save(journalEntity);
    }

    public List<JournalEntity> getAllJournal() {
        return javaEntityRepository.findAll();
    }

    public Optional<JournalEntity> getJournalById(ObjectId id) {
        return javaEntityRepository.findById(id);// optional return , mean if present then return or null,
    }

    public void deleteJournal(ObjectId id) {
        javaEntityRepository.deleteById(id);
    }
}