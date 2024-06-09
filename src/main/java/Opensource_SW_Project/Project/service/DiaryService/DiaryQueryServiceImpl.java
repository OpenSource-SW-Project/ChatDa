package Opensource_SW_Project.Project.service.DiaryService;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.repository.DiaryRepository;
import Opensource_SW_Project.Project.repository.MemberRepository;
import Opensource_SW_Project.Project.repository.TalkRepository;
import Opensource_SW_Project.Project.web.controller.TalkController;
import Opensource_SW_Project.Project.web.dto.Diary.DiaryRequestDTO;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Getter
public class DiaryQueryServiceImpl implements DiaryQueryService{

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final TalkController talkController;
    private final TalkRepository talkRepository;

    public Diary diaryFind(DiaryRequestDTO.GetDiaryRequestDTO request) {
        Optional<Diary> findDiary = diaryRepository.findById(request.getDiaryId());
        return null;
    }

    @Override
    public Diary findById(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).get();
        return diaryRepository.save(diary);
    }

    @Override
    public List<Diary> getDiaryByTalkId(Long talkId) {
        Talk getTalk = talkRepository.findById(talkId).get();
        List<Diary> diary = diaryRepository.findByTalk(getTalk);
        return diary;
    }

    @Override
    public List<Diary> getUserDiary(Long memberId) {
        Member getMember = memberRepository.findById(memberId).get();
        List<Diary> UserDiaryList = diaryRepository.findAllByMember(getMember);

        return UserDiaryList;
    }
}
