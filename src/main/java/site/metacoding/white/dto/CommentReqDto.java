package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.Comment;

public class CommentReqDto {

    @Setter
    @Getter
    public static class CommentSaveReqDto {
        private String content;
        private Long boardId;
        private SessionUser sessionUser; // 서비스 로직

        public Comment toEntity(Board board) {
            return Comment.builder()
                    .content(content)
                    .board(board)
                    .user(sessionUser.toEntity())
                    .build();
        }
    }
}
