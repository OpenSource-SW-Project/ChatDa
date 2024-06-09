package Opensource_SW_Project.Project.web.dto.Diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class DiaryResponseDTO{

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDiaryResultDTO {
        Long memberId;
        Long diaryId;
        String title;
        String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryDTO {
        //Long memberId;
        Long diaryId;
        String title;
        String content;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryPreviewDTO {    // 특정 일기 조회
        Long memberId;
        Long diaryId;
        String title;
        String content;

    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateDiaryResultDTO {
        Long memberId;
        Long diaryId;
        String title;
        String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDiaryResultDTO {
        Long diaryId;
        String title;
        String content;
        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDiaryResultListDTO {
        List<UserDiaryResultDTO> diaries;
    }
}
