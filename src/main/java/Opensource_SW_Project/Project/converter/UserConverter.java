package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.web.dto.User.UserRequestDTO;
import Opensource_SW_Project.Project.web.dto.User.UserResponseDTO;

import java.util.List;

public class UserConverter {

    public static Member toMember(UserRequestDTO.CreateUserRequestDTO request, List<String> roles){
        return Member.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(roles)
                .build();
    }

    public static UserResponseDTO.CreateUserResultDTO toCreateUserResultDTO(Member member) {
        return UserResponseDTO.CreateUserResultDTO.builder()
                .userId(member.getUserId())
                .name(member.getName())
                .build();
    }
}
