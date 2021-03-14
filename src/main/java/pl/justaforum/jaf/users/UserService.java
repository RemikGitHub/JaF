package pl.justaforum.jaf.users;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.justaforum.jaf.email.EmailService;
import pl.justaforum.jaf.token.Token;
import pl.justaforum.jaf.token.TokenService;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        Token token = new Token(user);
        tokenService.saveToken(token);

        sendConfirmationMail(user.getEmail(), token.getToken());
    }

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

    void sendConfirmationMail(String userMail, String token) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Confirm your account!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "If you want to activate your account, click on the link. \n" + "http://localhost:8080/signup/confirm?token=" + token);

        emailService.sendEmail(mailMessage);
    }

}
