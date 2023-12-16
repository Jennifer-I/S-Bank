package com.jennifer.sbank.security;

import com.jennifer.sbank.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
//@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
@EnableWebSecurity
public class SecurityConfiguration {
    private final SBankFilter sbankFilter;

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("error").permitAll()
                        .requestMatchers("/auth/**").permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/Transaction/**")).hasAnyRole(Role.CUSTOMER.name(), Role.ADMIN.name())
                        .anyRequest().authenticated())
                .addFilterBefore(sbankFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService, JWTService jwtService) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .cors()
//                .and()
//                .authorizeHttpRequests(
//                        c -> c
//                                .requestMatchers("error").permitAll()
//                                .requestMatchers("/api/v1/employee/**")
//                                .requestMatchers(GET, basePath + "/employee/**")
//                                .requestMatchers(POST, basePath + "/employee/**")
//                                .requestMatchers(PUT, basePath + "/employee/**")
//                                .requestMatchers(DELETE, basePath + "/employee/**")
//                                .anyRequest()
//                                .authenticated())
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(new JwtAuthenticationFilter(userDetailsService, jwtService), UsernamePasswordAuthenticationFilter.class)
//                .logout()
//                .logoutUrl(basePath + "/auth/logout")
//                .addLogoutHandler(logoutHandler)
//                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
//
//        return http.build();
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
