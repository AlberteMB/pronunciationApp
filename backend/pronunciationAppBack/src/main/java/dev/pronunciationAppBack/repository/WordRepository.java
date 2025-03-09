package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;


public interface WordRepository extends JpaRepository<Word, String> {

    // Pagination
    Page<Word> findAll(Pageable pageable);
    Page<Word> findAllByOrderByWordNameAsc(Pageable pageable);
    Page<Word> findAllByOrderByWordNameDesc(Pageable pageable);

    Word getWordById(String id);
    Word getWordByPhoneticSpelling(String pronunciation);
}
