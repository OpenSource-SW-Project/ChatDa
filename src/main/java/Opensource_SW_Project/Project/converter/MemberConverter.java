package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.web.dto.Member.MemberRequestDTO;
import Opensource_SW_Project.Project.web.dto.Member.MemberResponseDTO;

import java.util.List;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.CreateUserRequestDTO request, String encodedPassword, List<String> roles){
        return Member.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(encodedPassword)
                .roles(roles)
                .build();
    }

    public static MemberResponseDTO.CreateUserResultDTO toCreateUserResultDTO(Member member) {
        return MemberResponseDTO.CreateUserResultDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .build();
    }
}
