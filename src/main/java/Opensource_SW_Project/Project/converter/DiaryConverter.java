package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryResponseDTO;


public class DiaryConverter {
    public static DiaryResponseDTO.CreateDiaryResultDTO toCreateDiaryResultDTO(Diary diary) {
        return DiaryResponseDTO.CreateDiaryResultDTO.builder()
                .userId(diary.getUser().getUserId())
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }


    public static DiaryResponseDTO.UpdateDiaryResultDTO UpdateDiaryResultDTO(Diary diary) {
        return DiaryResponseDTO.UpdateDiaryResultDTO.builder()
                .userId(diary.getUser().getUserId())
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }

}
