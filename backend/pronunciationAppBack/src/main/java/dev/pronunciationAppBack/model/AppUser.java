package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Username is required")
    @NotNull
    private String userName;
    private int age;
    @NotBlank(message = "Username is required")
    @NotNull
    private String email;
    @NotNull
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
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
    @Override
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


