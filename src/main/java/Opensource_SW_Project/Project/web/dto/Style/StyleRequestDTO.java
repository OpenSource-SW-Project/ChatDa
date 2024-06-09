package Opensource_SW_Project.Project.web.dto.Style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StyleRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetStyleRequestDTO {
        private long memberId;
        private long styleId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStyleRequestDTO {
        private String userInput;  // 사용자가 입력한 문장을 받을 변수
    }
}
