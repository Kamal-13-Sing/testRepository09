package com.SpringSecurity01.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder(){

        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();

        return bpe;
    }


    //---security filterchain

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws  Exception{

        return httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/admin")
                .hasAnyRole("EDIT", "DELETE")
                .requestMatchers(HttpMethod.GET, "/customer")
                .hasAnyRole("VIEW")
                .and()
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    //---asign role to the user
@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("VIEW", "EDIT", "DELETE")
                .build();

        UserDetails customer = User
                .withUsername("customer")
                .password(passwordEncoder.encode("customer"))
                .roles("VIEW")
                .build();

        InMemoryUserDetailsManager inm = new InMemoryUserDetailsManager(admin,customer);

        return inm;

    }


}
