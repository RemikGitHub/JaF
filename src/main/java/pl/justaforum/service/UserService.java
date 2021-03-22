package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.justaforum.persistence.entity.Token;
import pl.justaforum.persistence.entity.User;
import pl.justaforum.persistence.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public void addUser(User user) {

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        Token token = new Token(user);
        tokenService.saveToken(token);

        emailService.sendEmail(user.getEmail(), token.getToken());
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean emailExists(String email) { return userRepository.findByEmail(email).isPresent(); }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user \"" + username + "\" does not exist."));
    }

    public void confirmUser(Token token) {
        User user = token.getUser();
        user.setEnabled(true);

        userRepository.save(user);
        tokenService.deleteToken(token.getId());
    }


}
