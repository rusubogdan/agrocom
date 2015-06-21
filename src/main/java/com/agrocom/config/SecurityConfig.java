package com.agrocom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                        // add a few others todo delete in the future
                .antMatchers("/", "/resources/**", "/j_spring_security_check", "favicon.ico",
                        "/register/**", "/sendMessage/**", "/home/**", "/home", "/ajax/**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .anyRequest()
    //                            .hasAnyRole("ADMIN", "USER", "MODERATOR")
                    .permitAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/societies")
                        .failureUrl("/login?error")
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")

                        .loginProcessingUrl("/j_spring_security_check")
                    .permitAll()
                .and()
                    .logout()
                        .addLogoutHandler(customLogoutHandler())
                        .logoutRequestMatcher(new AntPathRequestMatcher("/j_spring_security_logout"))
                        .deleteCookies("remove")
                        .invalidateHttpSession(true)
                        .logoutUrl("/j_spring_security_logout")
                        .logoutSuccessUrl("/login?logout")

                    .permitAll()


                .and()
                    .csrf()
                    .disable();
    }

    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler();
    }

    public class CustomLogoutHandler implements LogoutHandler {

        @Override
        public void logout(HttpServletRequest request,
                           HttpServletResponse httpServletResponse, Authentication authentication) {
            request.getSession().invalidate();
        }
    }

    /*@Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();

        logoutSuccessHandler.setTargetUrlParameter("redirect");
        logoutSuccessHandler.setDefaultTargetUrl("/login?logout");
        return logoutSuccessHandler;
    }*/

    /*public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        // Just for setting the default target URL
        public LogoutSuccessHandler(String defaultTargetURL) {
            this.setDefaultTargetUrl(defaultTargetURL);
        }

        @Override
        public void onLogoutSuccess(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {

            // do whatever you want
            super.onLogoutSuccess(request, response, authentication);
        }
    }*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
