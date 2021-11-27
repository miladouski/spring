package com.spring.project.config;

import com.spring.project.interfaces.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.spring.project.constants.ConstantsConfig.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountDAO accountDAO;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.userDetailsService(accountDAO).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(DEFAULT_URL, SECURITY_URL).authenticated()
                .and()
                .formLogin().loginPage(LOGIN_PAGE)
                .defaultSuccessUrl(DEFAULT_URL)
                .failureUrl(FAILURE_URL)
                .usernameParameter(USERNAME_PARAMETER).passwordParameter(PASSWORD_PARAMETER)
                .and()
                .logout().logoutSuccessUrl(LOGOUT_URL);
    }
}
