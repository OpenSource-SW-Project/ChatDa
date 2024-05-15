package Opensource_SW_Project.Project.web.controller;

import Opensource_SW_Project.Project.apiPayload.ApiResponse;
import Opensource_SW_Project.Project.apiPayload.code.status.SuccessStatus;
import Opensource_SW_Project.Project.converter.UserConverter;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.service.UserService.UserCommandService;
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
    @PostMapping("/")
    public ApiResponse<UserResponseDTO.CreateUserResultDTO> createUser(@RequestParam(name = "userName")String userName) {
        User newUser = userCommandService.createUser(userName);
        return ApiResponse.onSuccess(
                SuccessStatus.USER_OK,
                UserConverter.toCreateUserResultDTO(
                        newUser
                )
        );
    }
}
