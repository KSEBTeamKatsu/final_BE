package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.repository.JdbcMemberRepository;
import Katsu.Katsu_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 POST api 처리
    @PostMapping("/register")
    public ResponseEntity<String> signup(@RequestBody Member member) {
        // 디버깅용
        System.out.println("받은 ID: "+member.getId());
        System.out.println("받은 PW: "+member.getPw());
        try {
            memberService.join(member);
            return ResponseEntity.ok("회원가입 성공! 로그인 해주세요.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("에러: " + e.getMessage());
        }
    }



    // 로그인 POST api 처리
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member, HttpSession session) {
        // 디버깅용
        System.out.println("받은 ID: "+member.getId());
        System.out.println("받은 PW: "+member.getPw());
        Member found = memberService.findById(member.getId());
        // 아이디/비밀번호 검증
        if (found != null && found.getPw().equals(member.getPw())) {
            // 세션 처리, 세션은 새로 고침하면 사라짐.
            session.setAttribute("user", member.getId());
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","로그인 실패"));
        }
    }

    // 로그아웃 POST api 처리
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }

}
