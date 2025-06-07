package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Member member) {
        String sql = "INSERT INTO member_table(id, pw) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getId(), member.getPw());
    }

    @Override
    public Member findById(String id) {
        String sql = "SELECT * FROM member_table WHERE id = ?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper(), id);
        return result.stream().findAny().orElse(null);
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getString("id"));
            member.setPw(rs.getString("pw"));
            return member;
        };
    }
}

