package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.UserConverter;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.service.UserService.MemberCommandService;
import Opensource_SW_Project.Project.service.UserService.MemberQueryService;
import Opensource_SW_Project.Project.web.dto.JwtToken;
import Opensource_SW_Project.Project.web.dto.Member.MemberRequestDTO;
import Opensource_SW_Project.Project.web.dto.Member.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/users")
@Slf4j
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @Operation(summary = "유저 생성", description =
            "User를 생성합니다."
    )
    @PostMapping("/signUp")
    public ApiResponse<MemberResponseDTO.CreateUserResultDTO> createUser(
            @RequestBody MemberRequestDTO.CreateUserRequestDTO request
    ) {
        Member newMember = memberCommandService.signUp(request);
        return ApiResponse.onSuccess(
                SuccessStatus.USER_OK,
                UserConverter.toCreateUserResultDTO(
                        newMember
                )
        );
    }

    @PostMapping("/signIn")
    public ApiResponse<MemberResponseDTO.signInResultDTO> signIn(
            @RequestBody MemberRequestDTO.signInRequestDTO request
    ) {
        Member newMember = memberQueryService.getMember(request);
        JwtToken jwtToken = memberCommandService.signIn(request.getUsername(), request.getPassword());
        return ApiResponse.onSuccess(
                SuccessStatus.USER_OK,
                MemberResponseDTO.signInResultDTO.builder()
                        .userId(newMember.getUserId())
                        .accessToken(jwtToken.getAccessToken())
                        .build()
        );
    }

}
