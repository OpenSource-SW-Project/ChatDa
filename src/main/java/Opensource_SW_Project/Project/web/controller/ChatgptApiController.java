package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.domain.enums.TalkState;
import Opensource_SW_Project.Project.service.ChatgptApiService.ChatgptApiCommandService;
import Opensource_SW_Project.Project.service.ModerationService.ModerationService;
import Opensource_SW_Project.Project.web.dto.ChatgptApi.ChatgptApiRequestDTO;
import Opensource_SW_Project.Project.web.dto.ChatgptApi.ChatgptApiResponseDTO;
import Opensource_SW_Project.Project.web.dto.Talk.TalkRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/api")
@Slf4j
public class ChatgptApiController {

    private final ChatgptApiCommandService chatgptApiService;
    private final ModerationService moderationService;

    @PostMapping("/chat")
    public ApiResponse<ChatgptApiResponseDTO.SendMessageResultDTO> chat(
            @RequestParam(name = "memberId")Long memberId,
            @RequestBody TalkRequestDTO.CreateMessageRequestDTO request){ // memberId와 talkID 필요, 화제 바꾸는 프롬프트 부르는 Id값(enum) 필요함
        TalkState talkState = TalkState.COMMON;

        // userPrompt requestbody로 받기
        String userPrompt = chatgptApiService.getUserPrompt(request);

        // moderation 적용
        try {
            String moderation_message = moderationService.getChatCompletion(userPrompt);
            System.out.println("Moderation " + userPrompt + ": " + moderation_message);
            if(moderation_message != null) talkState = TalkState.WARNING;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 시스템 프롬프트 생성하는 메소드 <- 대화 id에 따라 과거 대화기록 가져오기, 기본 시스템 프롬프트 클래스, 조건 시스템 프롬프트 클래스 이용
        String message = chatgptApiService.generateSystemPrompt(memberId, request);

        // service에서 userPrompt와 chatGPTResponse 저장하기
        chatgptApiService.saveUserPromptAndMessage(request, userPrompt, message);

        return ApiResponse.onSuccess(
                SuccessStatus.MESSAGE_OK,
                ChatgptApiResponseDTO.SendMessageResultDTO.builder()
                        .message(message)
                        .talkState(talkState)
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


}
