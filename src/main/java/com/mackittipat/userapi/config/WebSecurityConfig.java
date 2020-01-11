package com.mackittipat.userapi.config;

import com.mackittipat.userapi.filter.JwtAuthenticationFilter;
import com.mackittipat.userapi.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Enabled h2-console
                .headers().frameOptions().disable() // Enabled h2-console
                .and()
                .authorizeRequests()
                .antMatchers("/api/users").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/h2-console/**").permitAll() // Enabled h2-console
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                // Spring Security will never create an HttpSession and
                // it will never use it to obtain the SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        http.addFilterAfter(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
        ;
    }

    /*
     * This allow @Autowired private AuthenticationManager authenticationManager;
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
