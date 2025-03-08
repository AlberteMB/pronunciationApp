package dev.pronunciationAppBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pronunciation {
    @Id
    private String id;
    private String audioDescription;
    private long audioDuration;
    private long audioSize;
    private String audioUrl;
    private String definition;
    private String phoneticSpelling;
    private String speakerGender;
    public enum type {
        RECORDED, SAMPLE
    }
    private type type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORD_ID_FK")
    private Word word;

    @Override
    public String toString() {
        return "Pronunciation{" +
                "id='" + id + '\'' +
                ", audioDescription='" + audioDescription + '\'' +
                ", audioDuration=" + audioDuration +
                ", audioSize=" + audioSize +
                ", audioUrl='" + audioUrl + '\'' +
                ", definition='" + definition + '\'' +
                ", phoneticSpelling='" + phoneticSpelling + '\'' +
                ", speakerGender='" + speakerGender + '\'' +
                ", type=" + type +
                '}';
    }
}
