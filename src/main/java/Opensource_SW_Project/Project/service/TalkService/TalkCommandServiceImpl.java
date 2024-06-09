package Opensource_SW_Project.Project.service.TalkService;

import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.repository.TalkRepository;
import Opensource_SW_Project.Project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TalkCommandServiceImpl implements TalkCommandService{

    private final MemberRepository memberRepository;
    private final TalkRepository talkRepository;

    public Talk createTalk(Long memberId){
        Member member = memberRepository.findById(memberId).get(); // 후에 예외처리 해주기, 해당하는 user가 존재하지 않을 때
        Talk newTalk = Talk.builder()
                .member(member)
                .build();
        Talk savedTalk = talkRepository.save(newTalk);
        return savedTalk;
    }
}
