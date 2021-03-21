package pl.justaforum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.justaforum.persistence.entity.Token;
import pl.justaforum.persistence.repository.TokenRepository;

import java.util.Optional;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public void deleteToken(Long id) {

        tokenRepository.deleteById(id);
    }

    public Optional<Token> findToken(String token) {

        return tokenRepository.findTokenByToken(token);
    }
}
