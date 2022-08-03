package com.sukhoev.psms.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    /*
    * Данный класс будет использоваться для принятия json запроса
    * при регистрации и преобразовании его в объект java
    * */
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
