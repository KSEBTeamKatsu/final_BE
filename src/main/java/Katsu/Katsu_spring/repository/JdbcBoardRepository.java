package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.dto.BoardDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class JdbcBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<BoardDTO> boardRowMapper = (rs, rowNum) -> {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setPostId(rs.getInt("postId"));
        boardDTO.setUserId(rs.getString("userId"));
        boardDTO.setTitle(rs.getString("title"));
        boardDTO.setContent(rs.getString("content"));
        boardDTO.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        boardDTO.setViewCnt(rs.getInt("viewCnt"));
        boardDTO.setLikes(rs.getInt("likes"));
        return boardDTO;
    };

    @Override
    public void posts(BoardDTO boardDTO, HttpSession session) {
        String sql = "INSERT INTO board_table (userId, title, content, createdAt, viewCnt, likes) " +
                "VALUES (?, ?, ?, NOW(), 0, 0)";
        jdbcTemplate.update(sql,
                session.getAttribute("user"),
                boardDTO.getTitle(),
                boardDTO.getContent());
    }

    @Override
    public List<BoardDTO> findAll() {
        log.info("findAll called");
        String sql = "SELECT postId, userId, title, content, createdAt, viewCnt, likes " +
                "FROM board_table ORDER BY postId DESC";
        return jdbcTemplate.query(sql, boardRowMapper);
    }

    @Override
    public void updateView(Long postId) {
        log.info("updateView");
        String sql = "UPDATE board_table SET viewCnt = viewCnt + 1 WHERE postId = ?";
        jdbcTemplate.update(sql, postId);
    }

    @Override
    public BoardDTO findById(Long postId) {
        log.info("findById");
        String sql = "SELECT postId, userId, title, content, createdAt, viewCnt, likes " +
                "FROM board_table WHERE postId = ?";
        return jdbcTemplate.queryForObject(sql, boardRowMapper, postId);
    }

    @Override
    public void update(BoardDTO boardDTO) {
        String sql = "UPDATE board_table SET title = ?, content = ? WHERE postId = ?";
        jdbcTemplate.update(sql,
                boardDTO.getTitle(),
                boardDTO.getContent(),
                boardDTO.getPostId());
    }

    @Override
    public void delete(long postId) {
        String sql = "DELETE FROM board_table WHERE postId = ?";
        jdbcTemplate.update(sql, postId);
    }

    @Override
    public void incrementLikes(long postId) {
        String sql = "UPDATE board_table SET likes = likes + 1 WHERE postId = ?";
        jdbcTemplate.update(sql, postId);
    }

    @Override
    public void decrementLikes(Long postId) {
        String sql = "UPDATE board_table SET likes = likes - 1 WHERE postId = ?";
        jdbcTemplate.update(sql, postId);
    }
}
