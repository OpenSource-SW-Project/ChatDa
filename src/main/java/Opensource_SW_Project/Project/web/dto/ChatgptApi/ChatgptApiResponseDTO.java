package Opensource_SW_Project.Project.web.dto.ChatgptApi;

import Opensource_SW_Project.Project.domain.enums.TalkState;
import lombok.*;

import java.util.List;

public class ChatgptApiResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendMessageResultDTO{
        String message;
        TalkState talkState;
    }
}
