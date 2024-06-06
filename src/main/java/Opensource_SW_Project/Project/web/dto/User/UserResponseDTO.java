package Opensource_SW_Project.Project.web.dto.User;

import Opensource_SW_Project.Project.web.dto.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserResultDTO {
        Long userId;
        String name;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class signInResultDTO {
        Long userId;
        JwtToken jwtToken;
    }
}
