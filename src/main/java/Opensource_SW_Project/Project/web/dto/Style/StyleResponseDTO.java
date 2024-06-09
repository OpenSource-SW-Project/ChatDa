package Opensource_SW_Project.Project.web.dto.Style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StyleResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStyleResultDTO {
        Long memberId;
        Long styleId;
        String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StyleDTO {
        Long styleId;
        String content;
    }
}
