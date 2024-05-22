package Opensource_SW_Project.Project.service.DiaryService;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryRequestDTO;
import Opensource_SW_Project.Project.web.dto.Talk.TalkRequestDTO;

public interface DiaryCommandService {
    String createDiarySystemPrompt(Long userId);
    Diary updateDiary(Long diaryId, DiaryRequestDTO.UpdateDiaryDTO request);
    Diary saveDiary(Long userId, TalkRequestDTO.CreateMessageRequestDTO request, String content);
}
