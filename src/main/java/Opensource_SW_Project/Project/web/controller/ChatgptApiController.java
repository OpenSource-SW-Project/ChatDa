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

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/api")
@Slf4j
public class ChatgptApiController {

    private final ChatgptApiService chatgptApiService;

    @PostMapping("/chat")
    public ApiResponse<ChatgptApiResponseDTO.SendMessageResultDTO> chat(
            @RequestParam(name = "userId")Long userId,
            @RequestBody TalkRequestDTO.CreateMessageRequestDTO request){ // userId와 talkID 필요, 화제 바꾸는 프롬프트 부르는 Id값(enum) 필요함
        // userPrompt requestbody로 받기
        String userPrompt = chatgptApiService.getUserPrompt(request);

        // 시스템 프롬프트 생성하는 메소드 <- 대화 id에 따라 과거 대화기록 가져오기, 기본 시스템 프롬프트 클래스, 조건 시스템 프롬프트 클래스 이용
        String message = chatgptApiService.generateSystemPrompt(userId, request);

        // service에서 userPrompt와 chatGPTResponse 저장하기
        chatgptApiService.saveUserPromptAndMessage(request, userPrompt, message);

        return ApiResponse.onSuccess(
                SuccessStatus.MESSAGE_OK,
                ChatgptApiResponseDTO.SendMessageResultDTO.builder()
                        .message(message)
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
