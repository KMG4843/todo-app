package _1.todo_app.service;

import _1.todo_app.entity.Todo;
import _1.todo_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // 페이징 처리를 위한 임포트
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo createTodo(Todo todo) {
        // 입력 데이터 유효성 검사
        if (todo.getTitle() == null || todo.getTitle().isEmpty()) {
            throw new IllegalArgumentException("할 일 제목은 필수 입력 사항입니다.");
        }
        return todoRepository.save(todo);
    }

    // 다량의 데이터 조회를 대비한 페이징 처리 로직
    public Page<Todo> getTodosPaging(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
