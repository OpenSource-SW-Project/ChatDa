package Opensource_SW_Project.Project.service;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.repository.DiaryRepository;
import Opensource_SW_Project.Project.repository.TalkRepository;
import Opensource_SW_Project.Project.repository.UserRepository;
import Opensource_SW_Project.Project.web.dto.TalkRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Transactional
@Slf4j
public class DiaryServiceImpl implements DiaryService{
    private final UserRepository userRepository;
    private final TalkRepository talkRepository;
    private final DiaryRepository diaryRepository;
    public String createDiarySystemPrompt(Long userId){
        User getUser = userRepository.findById(userId).get();

        String diarySystemPrompt = "다음은 " + getUser.getName() +"의 하루에 대한 대화 내용이다.\n" +
                getUser.getName() + "의 하루를 기록하는 일기를 작성하여라.\n" +
                "조건\n" +
                "1. 대화 내용을 통해 알 수 있는 [사용자 이름]의 하루를 담는다.\n" +
                "2. 형식에 맞춰 작성한다.\n" +
                "\n" +
                "형식\n" +
                "제목 : \n" +
                "내용 :\n" +
                "\n" +
                "대화 내용\n" +
                "[대화 내용 전체]\n";

        return diarySystemPrompt;
    }

    public Diary saveDiary(Long userId, TalkRequestDTO.CreateMessageRequestDTO request, String content){
        User getUser = userRepository.findById(userId).get();
        Talk getTalk = talkRepository.findById(request.getTalkId()).get();

        Diary newDiary = Diary.builder()
                .talk(getTalk)
                .user(getUser)
                .content(content)
                .build();
        Diary saveDiary = diaryRepository.save(newDiary);

        return saveDiary;
    }
}
