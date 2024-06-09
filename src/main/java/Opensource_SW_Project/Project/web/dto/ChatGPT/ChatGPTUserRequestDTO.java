package Opensource_SW_Project.Project.web.dto.ChatGPT;

import Opensource_SW_Project.Project.web.dto.Message.MessageDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTUserRequestDTO {
    private String model;
    private List<MessageDTO> messages;

    public ChatGPTUserRequestDTO(String model, String userInput) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new MessageDTO("user", userInput));
    }
}
