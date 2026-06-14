package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.JavaEntityRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntityServices {
    @Autowired
    private JavaEntityRepository javaEntityRepository;
    @Autowired
    UsersServices usersServices;
@Transactional
    public void saveEntry(JournalEntity journalEntity, String userName) {
        JournalEntity saved =  javaEntityRepository.save(journalEntity);// save in the journal_entity collection
        Users user = usersServices.findByUsername(userName);
        user.getJournalEntityList().add(saved);// then enter the saved data in the recomended user
        usersServices.saveEntry(user);// save the user .
    }
public void saveEntry(JournalEntity journalEntity) {
        javaEntityRepository.save(journalEntity);
}
    public List<JournalEntity> getAllJournal() {
        return javaEntityRepository.findAll();
    }

    public Optional<JournalEntity> getJournalById(ObjectId id) {
        return javaEntityRepository.findById(id);// optional return , mean if present then return or null,
    }

    public void deleteJournal(ObjectId id, String userName) {
        Users user = usersServices.findByUsername(userName);
        user.getJournalEntityList().removeIf(j -> j.getId().equals(id));
        usersServices.saveEntry(user);
        javaEntityRepository.deleteById(id);
    }
}