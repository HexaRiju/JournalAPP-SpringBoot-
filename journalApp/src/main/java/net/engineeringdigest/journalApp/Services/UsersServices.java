package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.JavaEntityRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UsersServices {
    @Autowired
    private UserRepository userRepository;
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUsersById(ObjectId id) {
        return userRepository.findById(id);// optional return , mean if present then return or null,
    }

    public void deleteUser(ObjectId id) {
        userRepository.deleteById(id);
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}