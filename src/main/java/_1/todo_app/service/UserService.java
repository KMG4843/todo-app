package _1.todo_app.service;

import _1.todo_app.entity.User;
import _1.todo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // 추가
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // SecurityConfig에서 만든 암호화 도구 주입

    // 회원가입
    public User signUp(User user) {
        // 과제 요구사항: 비밀번호를 암호화하여 저장합니다.
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }

    // 로그인
    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        // 암호화된 비밀번호는 equals가 아니라 matches()로 비교해야 합니다.
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        return null;
    }
}