package com.example.bitfactory.infrastructure.configuration;

import com.example.bitfactory.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final RedisDao redisDao;

    /*@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public SecurityConfig(UserService userService, RedisDao redisDao) {
        this.userService = userService;
        this.redisDao = redisDao;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("admin")
                .password("123").roles("admin")
                .and()
                .withUser("user")
                .password("123")
                .roles("user");*/
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .logoutUrl("/my/logout")
                .and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                //after
//                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                //3、addFilterBefore filter
                .addFilterBefore(new JwtFilter(redisDao), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

        /*http
                .formLogin();*/
    }
}
