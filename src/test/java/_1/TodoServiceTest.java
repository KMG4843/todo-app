package _1;

import _1.todo_app.entity.Todo;
import _1.todo_app.repository.TodoRepository;
import _1.todo_app.service.TodoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Mockito 도구 활용
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    @DisplayName("성공: 할 일이 정상적으로 저장된다.")
    void saveTodoSuccess() {
        // given (준비)
        Todo todo = new Todo();
        todo.setTitle("테스트 제목");
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        // when (실행)
        Todo savedTodo = todoService.createTodo(todo);

        // then (검증)
        assertThat(savedTodo.getTitle()).isEqualTo("테스트 제목");
    }

    @Test
    @DisplayName("실패: 제목이 없으면 IllegalArgumentException이 발생한다.")
    void saveTodoFail() {
        // given
        Todo todo = new Todo();
        todo.setTitle(""); // 빈 제목

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            todoService.createTodo(todo);
        });
    }
}