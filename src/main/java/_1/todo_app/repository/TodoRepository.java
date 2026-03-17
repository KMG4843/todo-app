package _1.todo_app.repository;

import _1.todo_app.entity.Todo;
import _1.todo_app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 특정 유저의 할 일 목록을 페이징 처리하여 가져오는 핵심 메서드
    Page<Todo> findByUser(User user, Pageable pageable);
}
