package Opensource_SW_Project.Project.web.dto.Talk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TalkRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMessageRequestDTO {
        private Long talkId;
        private String userPrompt;
    }
}
