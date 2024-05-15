package Opensource_SW_Project.Project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryResponseDTO{

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDiaryResultDTO {
        Long userId;
        Long diaryId;
        String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDiaryResultDTO {
        Long userId;
        Long diaryId;
        String content;
    }
}
