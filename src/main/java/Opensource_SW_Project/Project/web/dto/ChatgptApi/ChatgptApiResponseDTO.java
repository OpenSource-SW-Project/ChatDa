package Opensource_SW_Project.Project.web.dto.ChatgptApi;

import lombok.*;

import java.util.List;

public class ChatgptApiResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendMessageResultDTO{
        String message;
    }
}
