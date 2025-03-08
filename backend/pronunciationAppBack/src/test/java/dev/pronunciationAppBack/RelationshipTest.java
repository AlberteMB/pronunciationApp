package dev.pronunciationAppBack;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class RelationshipTest {

    @Autowired
    private WordRepository wordRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private GameProgressRepository gameProgressRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private StageWordRepository stageWordRepository;
    @Autowired
    private StageRepository stageRepository;

    @Test
    public void UserAppGameProgressTest() {
        // Creating and saving AppUser
        AppUser appUser = new AppUser("U001", "Juan", 20, "8lX5i@example.com",
                "password", 0, true);

        AppUser savedAppUser = appUserRepository.save(appUser);

        // Creating and saving GameProgress
        GameProgress gameProgress = new GameProgress("GP001", 0, GameProgress.Stage.STAGE_01,
                new java.util.Date(), 0, savedAppUser);

        GameProgress savedGameProgress = gameProgressRepository.save(gameProgress);

        // Saving the relationship
        savedAppUser.setGameProgress(savedGameProgress);
        appUserRepository.save(savedAppUser);

        // Saving the relationship
        savedGameProgress.setAppUser(savedAppUser);
        gameProgressRepository.save(savedGameProgress);

        // Checking the relationships
        assertNotNull(savedAppUser.getGameProgress(), "GameProgress must not be null");
        assertNotNull(savedGameProgress.getAppUser(), "AppUser must not be null");

        // Checking the relationships
        assertEquals(savedAppUser, savedGameProgress.getAppUser(), "AppUser in GameProgress must be the same");
        assertEquals(savedGameProgress, savedAppUser.getGameProgress(), "GameProgress in AppUser must be the same");
    }

    @Test
    public void WordCategoryTest() {
        // Create and save Word
        Word word = new Word("W001", "Example", "A thing characteristic of its kind",
                "ɪɡˈzæmpəl", "This is an example sentence.", true, 1);

        Word savedWord = wordRepository.save(word);

        // Create and save Category
        Category category = new Category("C001", "Nouns", "Words that represent people, places, things, or ideas",
                "Words that represent entities", 1);

        Category savedCategory = categoryRepository.save(category);

        // Save the relationship
        savedWord.getCategories().add(savedCategory);
        savedCategory.getWords().add(savedWord);

        // Save the relationships
        wordRepository.save(savedWord);
        categoryRepository.save(savedCategory);

        // Checking the relationships
        assertNotNull(savedWord.getCategories(), "The list of categories should not be null");
        assertNotNull(savedCategory.getWords(), "The list of words should not be null");

        // Checking the relationships
        assertEquals(1, savedWord.getCategories().size(), "Word must have one category");
        assertEquals("Nouns", savedWord.getCategories().get(0).getCategoryName(), "The name of the category must be 'Nouns'");

        // Checking the relationships
        assertEquals(1, savedCategory.getWords().size(), "Category must have one word");
        assertEquals("Example", savedCategory.getWords().get(0).getWordName(), "The name of the word must be 'Example'");
    }

    @Test
    public void testWordStageRelationship() {
        // Create and save Word
        Word word = new Word("W001", "Example", "A thing characteristic of its kind",
                "ɪɡˈzæmpəl", "This is an example sentence.", true, 1);
        Word savedWord = wordRepository.save(word);

        // Create and save Stage
        Stage stage = new Stage("S001", "Stage 1", "Avatar URL", "Status", 0, 0);
        Stage savedStage = stageRepository.save(stage);

        // Create and save StageWord
        StageWord stageWord = new StageWord("SW001", StageWord.Status.DONE, 1, new Date(), savedWord, savedStage);
        StageWord savedStageWord = stageWordRepository.save(stageWord);

        // Word and Stage relationship
        savedWord.getStageWords().add(savedStageWord);
        savedStage.getStageWords().add(savedStageWord);

        wordRepository.save(savedWord);
        stageRepository.save(savedStage);

        // Checking the relationships
        Optional<Word> retrievedWord = wordRepository.findById(savedWord.getId());
        assertTrue(retrievedWord.isPresent());
        assertEquals(1, retrievedWord.get().getStageWords().size());

        Optional<Stage> retrievedStage = stageRepository.findById(savedStage.getId());
        assertTrue(retrievedStage.isPresent());
        assertEquals(1, retrievedStage.get().getStageWords().size());

        Optional<StageWord> retrievedStageWord = stageWordRepository.findById(savedStageWord.getId());
        assertTrue(retrievedStageWord.isPresent());
        assertEquals(savedWord.getId(), retrievedStageWord.get().getWord().getId());
        assertEquals(savedStage.getId(), retrievedStageWord.get().getStage().getId());
    }



}
