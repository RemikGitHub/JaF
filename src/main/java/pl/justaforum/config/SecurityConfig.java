package pl.justaforum.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.justaforum.service.UserService;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_ENDPOINTS = {
            "/",
            "/login/**",
            "/signup/**"
    };

    public static final String[] AUTH_ENDPOINTS = {
            "/my-posts"
    };

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .antMatchers(AUTH_ENDPOINTS).authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/my-posts")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        /* disabling security to get to the h2 console
        http.csrf().disable();
        http.headers().disable();
        */

    }
}
