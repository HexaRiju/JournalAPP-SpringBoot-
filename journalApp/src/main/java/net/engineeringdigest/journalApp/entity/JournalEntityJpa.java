package net.engineeringdigest.journalApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entity")
@Data
public class JournalEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersJpa user;
}
