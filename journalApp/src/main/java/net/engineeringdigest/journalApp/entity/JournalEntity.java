package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Journal_entries")
@Data// lomboc (by default all anotation is there likr @ Getter @Setter .....)
public class JournalEntity {
    @Id
    private ObjectId id; // MongoDB by default store the id .
    private String title;
    private String contain;
    private LocalDateTime date;// automatically set the local date and time .
}
