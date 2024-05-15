package Opensource_SW_Project.Project.web.dto.ChatgptApi;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class ChatgptApiRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendMessageDTO{
        String message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Message {
        private String role;
        private String content;

    }
}
