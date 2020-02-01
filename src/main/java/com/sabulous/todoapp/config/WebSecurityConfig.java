package com.sabulous.todoapp.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
    // .and()
    // .cors().configurationSource(request -> new
    // CorsConfiguration().applyPermitDefaultValues());
    // }

    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    // CorsConfiguration configuration = new CorsConfiguration();
    // configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
    // "DELETE"));
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", configuration);
    // return source;
    // }

    public void configure(final HttpSecurity http) throws Exception {
        http.cors().configurationSource(new PermissiveCorsConfigurationSource()).and().csrf().disable()
                .authorizeRequests().antMatchers("**").permitAll();
    }

    private static class PermissiveCorsConfigurationSource implements CorsConfigurationSource {
        /**
         * Return a {@link CorsConfiguration} based on the incoming request.
         *
         * @param request
         * @return the associated {@link CorsConfiguration}, or {@code null} if none
         */
        @Override
        public CorsConfiguration getCorsConfiguration(final HttpServletRequest request) {
            final CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedOrigins(Collections.singletonList("*"));
            return configuration;
        }

    }

    // private AuthenticationProvider authenticationProvider;

    // @Autowired
    // public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
    //     this.authenticationProvider = authenticationProvider;
    // }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    //     final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //     authenticationProvider.setUserDetailsService(userDetailsService);
    //     return authenticationProvider;
    // }

    // @Autowired
    // private UserDetailsService userDetailsService;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // @Autowired
    // public void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService)
    //     .passwordEncoder(passwordEncoder)
    //     .and()
    //     .authenticationProvider(authenticationProvider);
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests().antMatchers("/", "/home", "/h2-console/**").permitAll()
    //             // DO NOT allow anything else
    //             // any request besides above line (ones in the antMatchers) require
    //             // authentication
    //             .anyRequest().authenticated()
    //             .and()
    //             .formLogin().loginPage("/login").permitAll()
    //             .and()
    //             .logout().permitAll();

    //     // Disable CSRF (cross site request forgery)
    //     http.csrf().disable();
    //     // required to enable accessing h2-console
    //     http.headers().frameOptions().disable();
    // }
  
}   