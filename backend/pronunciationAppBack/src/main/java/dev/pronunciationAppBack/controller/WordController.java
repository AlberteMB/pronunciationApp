package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping
    public ResponseEntity<?> getWords(@PageableDefault(size = 10, sort = "wordName") Pageable pageable) {
        HttpHeaders headers = getCommonHeaders("Get all words");

        Page<Word> wordsPage = wordService.getAllWords(pageable);

        return wordsPage.hasContent()
                ? new ResponseEntity<>(wordsPage, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/asc")
    public Page<Word> getAllWordsByOrderByWordNameAsc(@PageableDefault(size = 10, sort = "wordName")Pageable pageable) {
        HttpHeaders headers = getCommonHeaders("Get words sorted ASC");
        return wordService.getAllWordsByOrderByWordNameAsc(pageable);
    }
    @GetMapping("/desc")
    public Page<Word> getAllWordsByOrderByWordNameDesc(@PageableDefault(size = 10, sort = "wordName")Pageable pageable) {
        HttpHeaders headers = getCommonHeaders("Get words sorted DESC");
        return wordService.getAllWordsByOrderByWordNameDesc(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable String id) {
        Optional<Word> word = wordService.getWordById(id);
        HttpHeaders headers = getCommonHeaders("Get word by ID");

        return word.map(value -> new ResponseEntity<>(value, headers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(headers, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createWord")
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word createdWord = wordService.createWord(word);
        HttpHeaders headers = getCommonHeaders("Create a new word");

        return new ResponseEntity<>(createdWord, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable String id, @RequestBody Word word) {
        Word updatedWord = wordService.updateWord(word);
        HttpHeaders headers = getCommonHeaders("Update a word");

        return new ResponseEntity<>(updatedWord, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWord(@PathVariable("id") String idToDelete) {
        HttpHeaders headers = getCommonHeaders("Delete a word");

        if (wordService.existsById(idToDelete)) {
            wordService.deleteWord(idToDelete);
            return new ResponseEntity<>("Word deleted", headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Word not found", headers, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllWords() {
        wordService.deleteAllWords();
        HttpHeaders headers = getCommonHeaders("Delete all words");
        return new ResponseEntity<>("All words deleted", headers, HttpStatus.OK);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "Spring Boot");
        headers.add("version", "1.0.0");
        headers.add("word-count", String.valueOf(wordService.getWordCount()));
        headers.add("object", "words");
        return headers;
    }
}

