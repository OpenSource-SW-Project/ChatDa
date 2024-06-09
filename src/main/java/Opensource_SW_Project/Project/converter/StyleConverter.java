package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Style;
import Opensource_SW_Project.Project.web.dto.Style.StyleResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StyleConverter {

    public static StyleResponseDTO.CreateStyleResultDTO toCreateStyleResultDTO(Style style) {
        return StyleResponseDTO.CreateStyleResultDTO.builder()
                .memberId(style.getMember().getMemberId())
                .styleId(style.getStyleId())
                .content(style.getContent())
                .build();
    }

    public static StyleResponseDTO.UserStyleResultDTO toUserStyleResultDTO(Style style) {
        return StyleResponseDTO.UserStyleResultDTO.builder()
                .styleId(style.getStyleId())
                .content(style.getContent())
                .build();
    }

//    public static StyleResponseDTO.UserStyleResultListDTO toUserStyleResultListDTO(List<Style> userStyleList) {
//        List<StyleResponseDTO.UserStyleResultDTO> userStyleResultDTOList = IntStream.range(0, userStyleList.size())
//                .mapToObj(i->toUserStyleResultDTO(userStyleList.get(i)))
//                .collect(Collectors.toList());
//        return StyleResponseDTO.UserStyleResultListDTO.builder()
//                .styles(userStyleResultDTOList)
//                .build();
//    }
}
