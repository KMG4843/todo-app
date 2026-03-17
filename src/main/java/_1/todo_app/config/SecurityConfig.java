package _1.todo_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. 비밀번호 암호화 도구 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. 접근 권한 및 로그인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 테스트 환경이므로 CSRF 보안은 잠시 끕니다.
            .csrf(csrf -> csrf.disable())
            
            // 페이지별 접근 권한 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/user/signup", "/h2-console/**").permitAll() // 회원가입과 DB콘솔은 누구나 허용
                .anyRequest().authenticated() // 그 외 모든 요청은 로그인이 필요함
            )
            
            // 기본 로그인 폼 사용
            .formLogin(login -> login
                .defaultSuccessUrl("/api/todos", true) // 로그인 성공 시 이동할 페이지
                .permitAll()
            )
            
            // H2 콘솔을 브라우저에서 보기 위한 설정
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}