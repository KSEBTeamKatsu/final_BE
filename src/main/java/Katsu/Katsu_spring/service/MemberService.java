package Katsu.Katsu_spring.service;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.repository.JdbcMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final JdbcMemberRepository memberRepository;

    @Autowired
    public MemberService(JdbcMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member findById(String id) {
        return memberRepository.findById(id);
    }

    public void join(Member member) {
        if (memberRepository.findById(member.getId()) != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        memberRepository.save(member);
    }
}
