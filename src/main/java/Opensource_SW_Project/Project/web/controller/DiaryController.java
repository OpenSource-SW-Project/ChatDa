package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.DiaryConverter;
import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.service.ChatgptApiService.ChatgptApiService;
import Opensource_SW_Project.Project.service.DiaryService;
import Opensource_SW_Project.Project.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/diary")
@Slf4j
public class DiaryController {
    private final ChatgptApiService chatgptApiService;
    private final DiaryService diaryService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;
    @PostMapping   // 첫번째 로컬호스트 8080으로 들어오면 이것이 호출됨
    public ApiResponse<DiaryResponseDTO.CreateDiaryResultDTO> createDiary(
            @RequestParam(name = "userId")Long userId,
            @RequestBody TalkRequestDTO.CreateMessageRequestDTO request
            ){ // userId와 talkID 필요, 화제 바꾸는 프롬프트 부르는 Id값(enum) 필요함
        // 시스템 프롬프트 생성 메소드 만들기 <- 대화 id에 따라 과거 대화기록 가져오기, 기본 시스템 프롬프트 클래스, 조건 시스템 프롬프트 클래스
        String userPrompt1 = diaryService.createDiarySystemPrompt(userId);
        String userPrompt2 = chatgptApiService.getHistorytalk(userId, request);

        String userPrompt = userPrompt1 + userPrompt2;

        //String systemPrompt = "친근하게 대답해줘";
        // userPrompt requestbody로 받기
        String systemPrompt = "";
        // request를 api로 보내 chatGPT응답받기
        ChatGPTRequest chatGPTrequest = new ChatGPTRequest(model, systemPrompt,userPrompt);
        ChatGPTResponse chatGPTResponse =  template.postForObject(apiURL, chatGPTrequest, ChatGPTResponse.class);

        // service에서 userPrompt와 chatGPTResponse 저장하기
        Diary newDiary = diaryService.saveDiary(userId, request, chatGPTResponse.getChoices().get(0).getMessage().getContent());

        return ApiResponse.onSuccess(
                SuccessStatus.MESSAGE_OK,
                DiaryConverter.toCreateDiaryResultDTO(
                        newDiary
                )
        );
    }
}
