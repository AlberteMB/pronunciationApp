package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String categoryName;
    private String subCategoryName;
    private String description;
    private int wordCount;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Word> words;

    public Category(String id, String categoryName, String subCategoryName, String description, int wordCount) {
        this.id = id;
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
        this.description = description;
        this.wordCount = wordCount;
        this.words = new ArrayList<>();
    }


    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", description='" + description + '\'' +
                ", wordCount=" + wordCount +
                '}';
    }
}
