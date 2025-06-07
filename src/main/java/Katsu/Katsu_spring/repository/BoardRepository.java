package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.dto.BoardDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {

    void posts(BoardDTO boardDTO, HttpSession session);

    List<BoardDTO> findAll();

    void updateView(Long postId);

    BoardDTO findById(Long postId);

    void update(BoardDTO boardDTO);

    void delete(long postId);
}
