package pl.justaforum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.justaforum.service.validation.NewPasswordMatches;
import pl.justaforum.service.validation.OldPassword;
import pl.justaforum.service.validation.Password;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NewPasswordMatches
public class UserChangePasswordDto {

    @OldPassword
    String oldPassword;

    @Password
    String newPassword;

    String confirmNewPassword;
}
