package Opensource_SW_Project.Project.web.dto;

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
