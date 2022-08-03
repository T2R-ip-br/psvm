package com.sukhoev.psms.appuser.service;

import com.sukhoev.psms.appuser.entity.AppUser;
import com.sukhoev.psms.appuser.repository.AppUserRepository;
import com.sukhoev.psms.registration.token.ConfirmationToken;
import com.sukhoev.psms.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String signUpUser(AppUser appUser) {

        // проверяем свободна ли почта
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if(userExists) {
            throw new IllegalStateException("email already taken");
        }

        // кодируем пароль
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        // устанавливаем пользователю закодированный пароль
        appUser.setPassword(encodedPassword);

        // сохраняем пользователя в базе
        appUserRepository.save(appUser);

        // Send confirmation token / Отправить токен подтверждения
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
