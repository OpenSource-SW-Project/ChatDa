package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.web.dto.Talk.TalkResponseDTO;

public class TalkConverter {
    public static TalkResponseDTO.CreateTalkResultDTO toCreateTalkResultDTO(Talk talk) {
        return TalkResponseDTO.CreateTalkResultDTO.builder()
                .memberId(talk.getMember().getMemberId())
                .talkId(talk.getTalkId())
                .build();
    }
}
