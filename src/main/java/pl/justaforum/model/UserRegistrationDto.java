package pl.justaforum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.justaforum.service.validation.Password;
import pl.justaforum.service.validation.PasswordMatches;
import pl.justaforum.service.validation.UniqueEmail;
import pl.justaforum.service.validation.UniqueUsername;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserRegistrationDto {

    @UniqueUsername
    String username;

    @UniqueEmail
    String email;

    @Password
    String password;

    String confirmPassword;
}
