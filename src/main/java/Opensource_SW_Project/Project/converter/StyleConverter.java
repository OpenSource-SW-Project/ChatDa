package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Style;
import Opensource_SW_Project.Project.web.dto.Style.StyleResponseDTO;

public class StyleConverter {

    public static StyleResponseDTO.CreateStyleResultDTO toCreateStyleResultDTO(Style style) {
        return StyleResponseDTO.CreateStyleResultDTO.builder()
                .memberId(style.getMember().getMemberId())
                .styleId(style.getStyleId())
                .content(style.getContent())
                .build();
    }
}
