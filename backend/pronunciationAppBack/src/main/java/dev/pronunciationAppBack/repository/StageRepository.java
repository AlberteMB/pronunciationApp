package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, String> {
}
