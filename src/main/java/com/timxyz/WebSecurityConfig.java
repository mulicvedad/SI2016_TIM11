package com.timxyz;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


import com.timxyz.filters.JWTLoginFilter;
import com.timxyz.filters.JWTAuthenticationFilter;
import com.timxyz.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/").permitAll()
            // sad za sad cemo koristiti ovaj nacin upravljanja privilegijama 
            // razlog: @PreAuthorize("hasrole('ADMIN')") je neuspjesno testiran
            // NAPOMENA: Iako su role u bazi spasene sa prefiksom "ROLE_"
            // njega ne treba navoditi u metodi 'hasRole(string roleName)'
            .antMatchers("/accounts").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            // filtriramo sve zahtjeve za login sa nasim filterom JWTLoginFilter
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
            // sve ostale zahtjeve filtriramo sa nasim filterom JWTAuthenticationFilter
            .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    //CORS problemi - potrebno je dozvoliti da OPTIONS zahtjevi koje browser salje
    //stignu do servera
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(customUserDetailsService);
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("adminpass")
            .roles("ADMIN");
    }
}