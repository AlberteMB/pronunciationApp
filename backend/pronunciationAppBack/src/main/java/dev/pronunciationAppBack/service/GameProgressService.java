package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.GameProgress;
import dev.pronunciationAppBack.repository.GameProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameProgressService {

    @Autowired
    private GameProgressRepository gameProgressRepository;

    public List<GameProgress> getAllGameProgresses() {
        return gameProgressRepository.findAll();
    }

    public Optional<GameProgress> getGameProgressById(String id) {
        return Optional.ofNullable(gameProgressRepository.getGameProgressById(id));
    }

    public GameProgress createGameProgress(GameProgress gameProgress) {
        return gameProgressRepository.save(gameProgress);
    }

    public GameProgress updateGameProgress(GameProgress gameProgress) {
        return gameProgressRepository.save(gameProgress);
    }

    public void deleteGameProgress(String id) {
        gameProgressRepository.deleteById(id);
    }

    public void deleteAllGameProgresses() {
        gameProgressRepository.deleteAll();
    }

    public boolean existsById(String id) {
        return gameProgressRepository.existsById(id);
    }

    public long getGameProgressCount() {
        return gameProgressRepository.count();
    }

    // Additional business logic can be added here

}