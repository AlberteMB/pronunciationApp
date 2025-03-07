package dev.pronunciationAppBack;

import dev.pronunciationAppBack.model.AppUser;
import dev.pronunciationAppBack.model.Category;
import dev.pronunciationAppBack.model.GameProgress;
import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
        AppUser appUser = new AppUser("U001", "Juan", 20, "8lX5i@example.com", "password", 0, true);
        AppUser savedAppUser = appUserRepository.save(appUser);

        // Creating and saving GameProgress
        GameProgress gameProgress = new GameProgress("GP001", 0, GameProgress.Stage.STAGE_01, new java.util.Date(), 0, savedAppUser);
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
        Word word = new Word("W001", "Example", "A thing characteristic of its kind", "ɪɡˈzæmpəl", "This is an example sentence.", true, 1);
        Word savedWord = wordRepository.save(word);

        // Create and save Category
        Category category = new Category("C001", "Nouns", "Words that represent people, places, things, or ideas", "Words that represent entities", 1);
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



}
