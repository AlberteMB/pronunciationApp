package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String userName;
    private int age;
    private String email;
    private String password;
    private int totalScore;
    private boolean isActive;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private GameProgress gameProgress;

    public AppUser(String id, String userName, int age, String email, String password, int totalScore, boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.totalScore = totalScore;
        this.isActive = isActive;
        if (gameProgress == null) {
            this.gameProgress = new GameProgress();
    }
    }

    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", totalScore=" + totalScore +
                ", isActive=" + isActive +
                '}';
    }
}


