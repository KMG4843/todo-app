package _1.todo_app.controller;

import _1.todo_app.entity.User;
import _1.todo_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // POST /api/user/signup 으로 회원가입 요청을 받습니다.
    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        userService.signUp(user);
        return "회원가입이 완료되었습니다!";
    }
}