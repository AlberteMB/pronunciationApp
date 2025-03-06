package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StageWord {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int listenedQty;
    private Date lastUpdatedDateTime;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn( name = "WORD_ID", nullable = false)
    private Word word;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn( name = "STAGE_ID", nullable = false)
    private Stage stage;

    public enum Status {
        DONE, PENDING, FAIL
    }
}
