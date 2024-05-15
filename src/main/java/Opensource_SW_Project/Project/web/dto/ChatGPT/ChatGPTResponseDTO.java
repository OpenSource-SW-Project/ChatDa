package Opensource_SW_Project.Project.web.dto.ChatGPT;

import Opensource_SW_Project.Project.web.dto.Message.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTResponseDTO {
    private List<Choice> choices;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private MessageDTO message;

    }
}
