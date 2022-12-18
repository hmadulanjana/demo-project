package net.codinghermit.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, \"password\", enabled from users where username=?")
            .authoritiesByUsernameQuery("select username, \"role\" from users where username=?");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/*", "/login").permitAll()
                .antMatchers("/api/sec/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin() // Required for cookies even if form not needed.
                // .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/login", true)
                .and()
            .logout()
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .logoutUrl("/logout")
                    // .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
            .httpBasic()
                // .and()
                // .exceptionHandling()
                // // .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
                // // .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                // .accessDeniedHandler( (request, response, exception) ->
                //      response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage()
                // ))
                .and()
            .cors()
                // .disable()
                .and()
            .headers()
		        .frameOptions()
			    // .sameOrigin()
                .disable()
            .   and()
            .csrf()
                .disable()
            ;

			return http.build();
    }
}
