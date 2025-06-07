package Katsu.Katsu_spring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SessionController {

    @GetMapping("/session-check")
    public ResponseEntity<?> sessionCheck(HttpSession session) {
        Object user = session.getAttribute("user"); // user 속성으로 로그인 사용자 저장
        if (user != null) {
            return ResponseEntity.ok().body("세션 살아있음: " + user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션 없음");
        }
    }
}
