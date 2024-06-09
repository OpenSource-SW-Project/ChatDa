package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.JWT.JwtTokenProvider;
import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.StyleConverter;
import Opensource_SW_Project.Project.domain.Style;
import Opensource_SW_Project.Project.service.ChatgptApiService.ChatgptApiCommandService;
import Opensource_SW_Project.Project.service.StyleService.StyleCommandService;
import Opensource_SW_Project.Project.service.StyleService.StyleQueryService;
import Opensource_SW_Project.Project.web.dto.ChatGPT.ChatGPTRequestDTO;
import Opensource_SW_Project.Project.web.dto.ChatGPT.ChatGPTResponseDTO;
import Opensource_SW_Project.Project.web.dto.Style.StyleRequestDTO;
import Opensource_SW_Project.Project.web.dto.Style.StyleResponseDTO;
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
@RequestMapping("/style")
@Slf4j
public class StyleController {

    private final ChatgptApiCommandService chatgptApiService;
    private final StyleCommandService styleCommandService;
    private final StyleQueryService styleQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    @Operation(
            summary = "문체 생성 API",
            description = "일기 작성에 사용될 문체를 생성합니다. Param으로 memberId와 RequestBody에 입력한 내용을 넣으세요",
            security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<StyleResponseDTO.CreateStyleResultDTO> createStyle(
            @RequestParam(name = "memberId") Long memberId,
            @RequestBody StyleRequestDTO.CreateStyleRequestDTO request) {

        jwtTokenProvider.isValidToken(memberId);

        // 사용자 입력을 받아오기
        String userInput = request.getUserInput();

        // ChatGPT를 이용해 문체 생성
        String styleContent = chatgptApiService.generateStyle(userInput);
        Style newStyle = styleCommandService.saveStyle(memberId, styleContent);

        // 응답 생성
        return ApiResponse.onSuccess(
                SuccessStatus.STYLE_OK,
                StyleConverter.toCreateStyleResultDTO(newStyle)
        );
    }


    @GetMapping("/styleList/{memberId}")
    @Operation(
            summary = "유저가 생성한 문체 조회 API"
            , description = "로그인된 유저가 생성한 문체를 조회할 수 있습니다."
            , security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<StyleResponseDTO.UserStyleResultListDTO> findUserStyle(
            @PathVariable Long memberId
    ) {
        // 토큰 유효성 검사 (memberId)
        jwtTokenProvider.isValidToken(memberId);
        List<Style> userStyleList = styleQueryService.getUserStyle(memberId);
        return ApiResponse.onSuccess(
                SuccessStatus.STYLE_OK,
                StyleConverter.toUserStyleResultListDTO(userStyleList)
        );
    }

}
