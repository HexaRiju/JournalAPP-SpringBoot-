package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Journal_entries")
@Data// lombok (by default all anotation is there like @ Getter @Setter .....)
@NoArgsConstructor// this required during deserialization. or 400 bad request come,
public class JournalEntity {
    @Id
    private ObjectId id; // MongoDB by default store the id .
    private String title;
    private String contain;
    private LocalDateTime date;// automatically set the local date and time .
}
