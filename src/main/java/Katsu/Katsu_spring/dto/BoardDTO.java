package Katsu.Katsu_spring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int postId;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int viewCnt;
    private int likes;
}
