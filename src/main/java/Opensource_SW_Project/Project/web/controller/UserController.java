package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.UserConverter;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.service.UserService.UserCommandService;
import Opensource_SW_Project.Project.web.dto.JwtToken;
import Opensource_SW_Project.Project.web.dto.User.UserRequestDTO;
import Opensource_SW_Project.Project.web.dto.User.UserResponseDTO;
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
public class UserController {

    private final UserCommandService userCommandService;

    @Operation(summary = "유저 생성", description =
            "User를 생성합니다."
    )
    @PostMapping("/signUp")
    public ApiResponse<UserResponseDTO.CreateUserResultDTO> createUser(
            @RequestBody UserRequestDTO.CreateUserRequestDTO request
    ) {
        Member newMember = userCommandService.createUser(request);
        return ApiResponse.onSuccess(
                SuccessStatus.USER_OK,
                UserConverter.toCreateUserResultDTO(
                        newMember
                )
        );
    }

    @PostMapping("/signIn")
    public ApiResponse<JwtToken> signIn(
            @RequestBody UserRequestDTO.signInRequestDTO request
    ) {
        String username = request.getUsername();
        String password = request.getPassword();
        JwtToken jwtToken = userCommandService.signIn(username, password);
        return ApiResponse.onSuccess(
                SuccessStatus.USER_OK,
                jwtToken
        );
    }
}
