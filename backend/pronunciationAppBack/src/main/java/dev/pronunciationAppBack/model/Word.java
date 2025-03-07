package dev.pronunciationAppBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Word {

    @Id
    private String id;
    private String wordName;
    private String definition;
    private String phoneticSpelling;
    private String sentence;
    private boolean isActive;
    private int level;


    @OneToMany(mappedBy = "word")
    private List<Pronunciation> pronunciations;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "WORD_CATEGORY",
            joinColumns = @JoinColumn(name = "WORD_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID_FK"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL,
    fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StageWord> stageWords;

    public Word(String id, String wordName, String definition, String phoneticSpelling, String sentence, boolean isActive, int  level) {
        this.id = id;
        this.wordName = wordName;
        this.definition = definition;
        this.phoneticSpelling = phoneticSpelling;
        this.sentence = sentence;
        this.isActive = isActive;
        this.level = level;
        this.pronunciations = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.stageWords = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", wordName='" + wordName + '\'' +
                ", definition='" + definition + '\'' +
                ", phoneticSpelling='" + phoneticSpelling + '\'' +
                ", sentence='" + sentence + '\'' +
                ", isActive=" + isActive +
                ", level=" + level +
                '}';
    }
}
