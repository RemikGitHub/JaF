package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.justaforum.model.UserDto;
import pl.justaforum.model.UserRegistrationDto;
import pl.justaforum.persistence.entity.Token;
import pl.justaforum.persistence.entity.User;
import pl.justaforum.persistence.repository.UserRepository;
import pl.justaforum.utils.LoggedUser;
import pl.justaforum.utils.UserConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public List<UserDto> getUsersList() {
        List<User> result = userRepository.findAll();
        return result.stream().map(UserConverter::createUserDto).collect(Collectors.toList());
    }

    public UserDto getUserByUsername(String username){
        return UserConverter.createUserDto(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user \"" + username + "\" does not exist.")));
    }

    public void addUser(UserRegistrationDto request) {

        User user = UserConverter.createUser(request);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        Token token = new Token(user);
        tokenService.saveToken(token);

        emailService.sendEmail(user.getEmail(), token.getToken());
    }

    public User getUserEntityByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user \"" + username + "\" does not exist."));
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

    public void delUserById(Long id) throws RuntimeException {

        Optional<User> optionalUser = userRepository.findById(id);

        if( optionalUser.isEmpty() ) throw new UsernameNotFoundException("The user does not exist.");
        if ( !optionalUser.get().getUsername().equals(LoggedUser.getLoggedUsername())) throw new RuntimeException("You are not authorized!");

        userRepository.deleteById(id);

    }
}
