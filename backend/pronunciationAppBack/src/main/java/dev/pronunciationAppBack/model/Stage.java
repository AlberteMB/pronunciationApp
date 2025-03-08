package dev.pronunciationAppBack.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String stageName;
    private String avatarURl;
    private String status;
    private int progress;
    private int currentScore;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL,
    fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StageWord> stageWords;

    @Override
    public String toString() {
        return "Stage{" +
                "id='" + id + '\'' +
                ", stageName='" + stageName + '\'' +
                ", avatarURl='" + avatarURl + '\'' +
                ", status='" + status + '\'' +
                ", progress=" + progress +
                ", currentScore=" + currentScore +
                '}';
    }
}
