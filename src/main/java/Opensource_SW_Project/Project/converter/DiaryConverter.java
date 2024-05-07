package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.web.dto.DiaryResponseDTO;
import Opensource_SW_Project.Project.web.dto.TalkResponseDTO;

public class DiaryConverter {
    public static DiaryResponseDTO.CreateDiaryResultDTO toCreateDiaryResultDTO(Diary diary) {
        return DiaryResponseDTO.CreateDiaryResultDTO.builder()
                .userId(diary.getUser().getUserId())
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }
}
