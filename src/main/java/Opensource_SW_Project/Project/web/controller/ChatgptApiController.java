package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.web.dto.ChatGPTRequest;
import Opensource_SW_Project.Project.web.dto.ChatGPTResponse;
import Opensource_SW_Project.Project.web.dto.ChatgptApiRequestDTO;
import Opensource_SW_Project.Project.web.dto.ChatgptApiResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/api")
@Slf4j
public class ChatgptApiController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public ApiResponse<ChatgptApiResponseDTO.SendMessageResultDTO> chat(@RequestParam(name = "userPrompt")String userPrompt){
        String systemPrompt = "말 끝마다 '끼얏호우'를 붙여서 대답해 줘.";
        ChatGPTRequest request = new ChatGPTRequest(model, systemPrompt,userPrompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, request, ChatGPTResponse.class);
        return ApiResponse.onSuccess(
                SuccessStatus.MESSAGE_OK,
                ChatgptApiResponseDTO.SendMessageResultDTO.builder()
                        .message(chatGPTResponse.getChoices().get(0).getMessage().getContent())
                        .build()
        );
    }

    @PostMapping("/test")
    @Operation(
            summary = "ChatGPT API"
            , description = "질문할 내용을 입력하세요"
    )
    public ApiResponse<ChatgptApiResponseDTO.SendMessageResultDTO> sendMessage(
            @RequestBody ChatgptApiRequestDTO.SendMessageDTO request
    ){
        System.out.println(request.getMessage());
        return ApiResponse.onSuccess(
                SuccessStatus.MESSAGE_OK,
                ChatgptApiResponseDTO.SendMessageResultDTO.builder()
                        .message(request.getMessage())
                        .build()
        );
    }

    // OpenAiChatClient
    //private final OpenAiChatClient chatClient;

}
