package com.franktran.jsp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static com.franktran.jsp.config.security.UserRole.ADMIN;
import static com.franktran.jsp.config.security.UserRole.ADMINTRAINEE;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(Environment environment, PasswordEncoder passwordEncoder) {
        this.environment = environment;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/common.css", "/common.js", "/login/**", "/webjars/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/process-login")
                .and()
                .rememberMe() // default is 2 weeks
                .and().logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails frank = User.builder()
                .username("frank")
                .password(passwordEncoder.encode("frank123"))
                .authorities(ADMIN.getAuthorities())
                .build();

        UserDetails henry = User.builder()
                .username("henry")
                .password(passwordEncoder.encode("henry123"))
                .authorities(ADMINTRAINEE.getAuthorities())
                .build();


        return new InMemoryUserDetailsManager(frank, henry);
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> response.sendRedirect(environment.getProperty("server.servlet.context-path"));
    }

    private AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, e) -> {
            request.getSession().setAttribute("error", "Your username or password invalid");
            response.sendRedirect(String.format("%s/login", environment.getProperty("server.servlet.context-path")));
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            request.getSession().setAttribute("message", "Logout successful!");
            response.sendRedirect(environment.getProperty("server.servlet.context-path"));
        };
    }

}
