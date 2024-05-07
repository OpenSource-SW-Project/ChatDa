package Opensource_SW_Project.Project.service.DiaryService;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.web.dto.TalkRequestDTO;

public interface DiaryService {
    String createDiarySystemPrompt(Long userId);

    Diary saveDiary(Long userId, TalkRequestDTO.CreateMessageRequestDTO request, String content);
}
