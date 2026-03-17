package _1.todo_app.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 여기서 잡아냅니다.
public class GlobalExceptionHandler {

    // IllegalArgumentException(부적절한 값 입력)이 발생했을 때 실행됩니다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
        // 400 Bad Request 상태코드와 함께 에러 메시지를 JSON 형태로 반환합니다.
        return ResponseEntity.badRequest().body(Map.of(
            "status", 400,
            "error", "잘못된 요청",
            "message", e.getMessage()
        ));
    }
}