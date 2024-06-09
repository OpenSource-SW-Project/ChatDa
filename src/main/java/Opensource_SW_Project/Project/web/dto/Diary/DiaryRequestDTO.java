package Opensource_SW_Project.Project.web.dto.Diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DiaryRequestDTO {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetDiaryRequestDTO {
        private long memberId;
        private long diaryId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDiaryDTO {
        Long memberId;
        String title;
        String content;
    }
}
