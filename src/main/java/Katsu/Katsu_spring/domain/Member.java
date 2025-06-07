package Katsu.Katsu_spring.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Member {
    private String id;        // 아이디 (번호)
    private String pw;  // 비밀번호

    public Member() {}

    public Member(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

}