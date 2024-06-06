package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.JWT.JwtTokenProvider;
import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.TalkConverter;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.service.TalkService.TalkCommandService;
import Opensource_SW_Project.Project.web.dto.Talk.TalkResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/talk")
@Slf4j
public class TalkController {
    private final TalkCommandService talkCommandService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "대화 생성", description =
            "새로운 대화를 생성합니다."
    )
    @PostMapping("/")
    public ApiResponse<TalkResponseDTO.CreateTalkResultDTO> createTalk(
            @RequestParam(name = "userId")Long userId
    ) {
        Talk newTalk = talkCommandService.createTalk(userId);
        // 토큰 유효성 검사 (userId)
        jwtTokenProvider.isValidToken(userId);
        return ApiResponse.onSuccess(
                SuccessStatus.TALK_OK,
                TalkConverter.toCreateTalkResultDTO(
                        newTalk
                )
        );
    }
}
