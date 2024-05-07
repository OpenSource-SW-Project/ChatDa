package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.service.ChatgptApiService.ChatgptApiService;
import Opensource_SW_Project.Project.web.dto.*;
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

    private final ChatgptApiService chatgptApiService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @PostMapping("/chat")
    public ApiResponse<ChatgptApiResponseDTO.SendMessageResultDTO> chat(
            @RequestParam(name = "userId")Long userId,
            @RequestBody TalkRequestDTO.CreateMessageRequestDTO request){ // userId와 talkID 필요, 화제 바꾸는 프롬프트 부르는 Id값(enum) 필요함
        // 시스템 프롬프트 생성 메소드 만들기 <- 대화 id에 따라 과거 대화기록 가져오기, 기본 시스템 프롬프트 클래스, 조건 시스템 프롬프트 클래스
        //String systemPrompt = chatgptApiService.getDefaultSystemPrompt(request);
        String systemPrompt = "문장뒤에 . 붙여줘";

        // userPrompt requestbody로 받기
        String userPrompt = chatgptApiService.getUserPrompt(request);
        // request를 api로 보내 chatGPT응답받기
        ChatGPTRequest chatGPTrequest = new ChatGPTRequest(model, systemPrompt,userPrompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, chatGPTrequest, ChatGPTResponse.class);

        // service에서 userPrompt와 chatGPTResponse 저장하기
        chatgptApiService.saveUserPromptAndMessage(request, userPrompt, chatGPTResponse.getChoices().get(0).getMessage().getContent());


        return ApiResponse.onSuccess(
                SuccessStatus.MESSAGE_OK,
                ChatgptApiResponseDTO.SendMessageResultDTO.builder()
                        .message(chatGPTResponse.getChoices().get(0).getMessage().getContent())
                        .build()
        );
    }

    // 일기 저장 api 생성

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
