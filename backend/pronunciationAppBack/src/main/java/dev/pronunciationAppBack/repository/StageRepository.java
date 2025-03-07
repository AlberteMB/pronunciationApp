package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, String> {
    Stage getStageByStageName(String name);

    Stage getStageById(String id);
}
