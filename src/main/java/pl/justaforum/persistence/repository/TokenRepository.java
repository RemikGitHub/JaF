package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByToken(String token);
}
