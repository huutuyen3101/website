package com.example.webmvc.config;

import com.example.webmvc.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF protection
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/*").permitAll()  // Cho phép tất cả các yêu cầu đến URL "/"
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // Yêu cầu có quyền "ADMIN" đối với các URL bắt đầu bằng "/admin/"
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .anyRequest().authenticated() // Các yêu cầu khác đều cần xác thực
                )
                .formLogin(login -> login
                        .loginPage("/login") // Trang đăng nhập
                        .loginProcessingUrl("/login") // URL xử lý đăng nhập
                        .usernameParameter("username") // Tham số tên người dùng trong form đăng nhập
                        .passwordParameter("password") // Tham số mật khẩu trong form đăng nhập
                        .defaultSuccessUrl("/admin", true)) // URL chuyển hướng sau khi đăng nhập thành công

                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user",true))

                .logout(logout->logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
        return http.build();
    }



    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/fe/**", "assets/**","uploads/**");
    }

}
