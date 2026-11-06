package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntityJpaRepository extends JpaRepository<JournalEntityJpa, Long >{
}
