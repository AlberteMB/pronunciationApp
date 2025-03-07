package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.AppUser;
import dev.pronunciationAppBack.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getAppUserById(String id) {
        return Optional.ofNullable(appUserRepository.getAppUserById(id));
    }

    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser updateAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public void deleteAppUser(String id) {
        appUserRepository.deleteById(id);
    }

    public void deleteAllAppUsers() {
        appUserRepository.deleteAll();
    }

    public boolean existsById(String id) {
        return appUserRepository.existsById(id);
    }

    public long getAppUserCount() {
        return appUserRepository.count();
    }


}