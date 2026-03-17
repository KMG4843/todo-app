package _1.todo_app.repository;

import _1.todo_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자를 찾는 기능을 추가합니다 (로그인 시 사용)
    Optional<User> findByEmail(String email);
}