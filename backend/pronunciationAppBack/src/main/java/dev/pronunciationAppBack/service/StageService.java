package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Stage;
import dev.pronunciationAppBack.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Optional<Stage> getStageById(String id) {
        return Optional.ofNullable(stageRepository.getStageById(id));
    }

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Stage updateStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public void deleteStage(String id) {
        stageRepository.deleteById(id);
    }

    public void deleteAllStages() {
        stageRepository.deleteAll();
    }

    public boolean existsById(String id) {
        return stageRepository.existsById(id);
    }

    public long getStageCount() {
        return stageRepository.count();
    }

    // Additional business logic can be added here
    public Stage getStageByName(String name) {
        return stageRepository.getStageByStageName(name);
    }
}