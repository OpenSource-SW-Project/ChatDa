package Opensource_SW_Project.Project.web.dto.Talk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TalkResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateTalkResultDTO {
        Long userId;
        Long talkId;
    }
}
