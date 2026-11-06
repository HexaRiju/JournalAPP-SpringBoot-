package net.engineeringdigest.journalApp.repository;


import net.engineeringdigest.journalApp.entity.UsersJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersJpaRepository extends JpaRepository<UsersJpa, Long> {
}
