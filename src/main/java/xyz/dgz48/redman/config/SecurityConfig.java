package xyz.dgz48.redman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration about security.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception { // NOPMD
        // Define NOPMD because HttpSecurity$authorizeRequests throws Exception.
        http.authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**", "/lib/**", "/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/login").permitAll();
    }
}
