package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;

public class BoardReqDto {

    @Setter
    @Getter
    public static class BoardSaveReqDto {
        private String title;
        private String content;
        private SessionUser sessionUser; // 서비스 로직
    }

}
