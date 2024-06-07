package Opensource_SW_Project.Project.service.DiaryService;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.repository.DiaryRepository;
import Opensource_SW_Project.Project.repository.MemberRepository;
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
    public List<Diary> getUserDiary(Long userId) {
        Member getMember = memberRepository.findById(userId).get();
        List<Diary> UserDiaryList = diaryRepository.findAllByMember(getMember);

        return UserDiaryList;
    }
}
