package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.web.dto.TalkResponseDTO;
import Opensource_SW_Project.Project.web.dto.UserResponseDTO;

public class TalkConverter {
    public static TalkResponseDTO.CreateTalkResultDTO toCreateTalkResultDTO(Talk talk) {
        return TalkResponseDTO.CreateTalkResultDTO.builder()
                .userId(talk.getUser().getUserId())
                .talkId(talk.getTalkId())
                .build();
    }
}
