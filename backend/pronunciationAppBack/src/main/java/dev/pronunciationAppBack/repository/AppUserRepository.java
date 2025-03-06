package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
