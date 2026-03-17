package _1.todo_app.controller;

import _1.todo_app.entity.Todo;
import _1.todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor // 생성자 주입을 통해 todoService의 빨간 줄을 없애줍니다.
public class TodoController {

    private final TodoService todoService;

    // 1. 기본 전체 조회
    @GetMapping
    public List<Todo> list() {
        return todoService.getAllTodos();
    }

    // 2. 페이징 처리 조회 (과제 요구사항)
    @GetMapping("/paging")
    public Page<Todo> listPaging(Pageable pageable) {
        return todoService.getTodosPaging(pageable);
    }

    // 3. 할 일 등록
    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }
}