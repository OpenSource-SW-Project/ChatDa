package Opensource_SW_Project.Project.converter;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Hashtag;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DiaryConverter {
    public static DiaryResponseDTO.CreateDiaryResultDTO toCreateDiaryResultDTO(Diary diary) {
        return DiaryResponseDTO.CreateDiaryResultDTO.builder()
                .userId(diary.getMember().getUserId())
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }

    public static DiaryResponseDTO.DiaryDTO toDiaryDTO(Diary diary) {
        List<Hashtag> hashtagList = DiaryHashtagConverter.toHashtagList(
                diary.getDiaryHashTagList()
        );

        return DiaryResponseDTO.DiaryDTO.builder()
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }


    public static DiaryResponseDTO.UpdateDiaryResultDTO UpdateDiaryResultDTO(Diary diary) {
        return DiaryResponseDTO.UpdateDiaryResultDTO.builder()
                .userId(diary.getMember().getUserId())
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }

    public static DiaryResponseDTO.UserDiaryResultDTO toUserDiaryResultDTO(Diary diary) {
        return DiaryResponseDTO.UserDiaryResultDTO.builder()
                .diaryId(diary.getDiaryId())
                .content(diary.getContent())
                .build();
    }

    public static DiaryResponseDTO.UserDiaryResultListDTO toUserDiaryResultListDTO(List<Diary> userDiaryList) {
        List<DiaryResponseDTO.UserDiaryResultDTO> userDiaryResultDTOList = IntStream.range(0, userDiaryList.size())
                .mapToObj(i->toUserDiaryResultDTO(userDiaryList.get(i)))
                .collect(Collectors.toList());
        return DiaryResponseDTO.UserDiaryResultListDTO.builder()
                .diaries(userDiaryResultDTOList)
                .build();
    }

}
