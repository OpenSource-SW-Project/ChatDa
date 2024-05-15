package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.web.dto.User.UserResponseDTO;

public class UserConverter {
    public static UserResponseDTO.CreateUserResultDTO toCreateUserResultDTO(User user) {
        return UserResponseDTO.CreateUserResultDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }
}
