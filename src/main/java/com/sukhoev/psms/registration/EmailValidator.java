package com.sukhoev.psms.registration;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

    public boolean test(String email) {
        // проверка почты на корректность
        return true;
    }
}
