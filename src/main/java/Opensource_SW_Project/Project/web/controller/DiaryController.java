package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.DiaryConverter;
import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.service.ChatgptApiService.ChatgptApiCommandService;
import Opensource_SW_Project.Project.service.DiaryService.DiaryCommandService;
import Opensource_SW_Project.Project.service.DiaryService.DiaryQueryService;
import Opensource_SW_Project.Project.web.dto.ChatGPT.ChatGPTRequestDTO;
import Opensource_SW_Project.Project.web.dto.ChatGPT.ChatGPTResponseDTO;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryRequestDTO;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryResponseDTO;
import Opensource_SW_Project.Project.web.dto.Talk.TalkRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/diary")
@Slf4j
public class DiaryController {
    private final ChatgptApiCommandService chatgptApiService;
    private final DiaryCommandService diaryCommandService;
    private final DiaryQueryService diaryQueryService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    // 일기 생성
    @PostMapping()
    @Operation(
            summary = "일기 생성 API"
            , description = "일기를 생성합니다. Param으로 userId과, RequestBody에 대화내역 talkId와 userPrompt를 입력하세요"
            , security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<DiaryResponseDTO.CreateDiaryResultDTO> createDiary(
            @RequestParam(name = "userId")Long userId,
            @RequestBody TalkRequestDTO.CreateMessageRequestDTO request
            ){
        String DiarySystemPrompt = diaryCommandService.createDiarySystemPrompt(userId);
        String userHistoryTalk = chatgptApiService.getHistorytalk(userId, request);
        String userPrompt = DiarySystemPrompt + userHistoryTalk;

        String systemPrompt = "";
        // request를 api로 보내 chatGPT응답받기
        ChatGPTRequestDTO chatGPTrequest = new ChatGPTRequestDTO(model, systemPrompt,userPrompt);
        ChatGPTResponseDTO chatGPTResponse =  template.postForObject(apiURL, chatGPTrequest, ChatGPTResponseDTO.class);

        // 일기 내용 파싱
        String diaryContent = chatGPTResponse.getChoices().get(0).getMessage().getContent();

        String[] splitContent = diaryContent.split("\n", 2); // \n으로 나눔. 첫 번째는 제목, 두 번째는 내용
        String title = splitContent[0].replace("제목: ", "").trim();
        String content = splitContent[1].replace("내용: ", "").trim();

        // service에서 userPrompt와 chatGPTResponse 저장
        //Diary newDiary = diaryCommandService.saveDiary(userId, request, chatGPTResponse.getChoices().get(0).getMessage().getContent());
        Diary newDiary = diaryCommandService.saveDiary(userId, request, title, content);

        return ApiResponse.onSuccess(
                SuccessStatus.DIARY_OK,
                DiaryConverter.toCreateDiaryResultDTO(
                        newDiary
                )
        );
    }


    // 일기 수정
    @PatchMapping("/{diaryId}")
    @Operation(
            summary = "일기 수정 API"
            , description = "일기를 수정합니다. Path variable로 diaryId를 입력 받고, RequestBody에 작성자 userId와 수정할 일기 content를 입력하세요"
            , security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<DiaryResponseDTO.UpdateDiaryResultDTO> updateDiary(
            @RequestBody DiaryRequestDTO.UpdateDiaryDTO request,
            @PathVariable Long diaryId
    ) {
        return ApiResponse.onSuccess(
                SuccessStatus.DIARY_OK,
                DiaryConverter.UpdateDiaryResultDTO(
                        diaryCommandService.updateDiary(diaryId, request)
                )
        );
    }


    // 특정 일기 조회
    @GetMapping("/{diaryId}")
    @Operation(
            summary = "특정 일기 조회 API"
            , description = "특정 일기를 조회합니다. Path variable로 조회할 diaryId를 입력하세요"
    )
    public ApiResponse<DiaryResponseDTO.DiaryDTO> findDiary(
            @PathVariable Long diaryId,
            @RequestParam(name = "userId")Long userId
    ) {
        Object request;
        Diary findDiary = diaryQueryService.findById(diaryId);
        return ApiResponse.onSuccess(
                SuccessStatus.DIARY_OK,
                DiaryConverter.toDiaryDTO(
                        findDiary
                )
        );
    }

    // 유저가 작성한 모든 일기 조회
    @GetMapping("/diaryList/{userId}")
    @Operation(
            summary = "유저가 작성한 일기 조회 API"
            , description = "로그인된 유저가 작성한 일기를 조회할 수 있습니다."
    )
    public ApiResponse<DiaryResponseDTO.UserDiaryResultListDTO> findUserDiary(
            @PathVariable Long userId
    ) {
        List<Diary> userDiaryList = diaryQueryService.getUserDiary(userId);
        return ApiResponse.onSuccess(
                SuccessStatus.DIARY_OK,
                DiaryConverter.toUserDiaryResultListDTO(userDiaryList)
        );
    }



}
