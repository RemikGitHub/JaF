package pl.justaforum.jaf.users;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.justaforum.jaf.posts.Post;
import pl.justaforum.jaf.validation.PasswordConstraint;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Please enter a valid email address.")
    private String email;

    @Column(unique = true)
    private String username;

    @PasswordConstraint
    private String password;

    @Builder.Default
    private UserRole role = UserRole.ROLE_USER;

    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
