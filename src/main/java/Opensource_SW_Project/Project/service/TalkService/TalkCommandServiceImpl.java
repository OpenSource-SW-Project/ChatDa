package Opensource_SW_Project.Project.service.TalkService;

import Opensource_SW_Project.Project.domain.Talk;
import Opensource_SW_Project.Project.domain.User;
import Opensource_SW_Project.Project.repository.TalkRepository;
import Opensource_SW_Project.Project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TalkCommandServiceImpl implements TalkCommandService{

    private final UserRepository userRepository;
    private final TalkRepository talkRepository;

    public Talk createTalk(Long userId){
        User user = userRepository.findById(userId).get(); // 후에 예외처리 해주기, 해당하는 user가 존재하지 않을 때
        Talk newTalk = Talk.builder()
                .user(user)
                .build();
        Talk savedTalk = talkRepository.save(newTalk);
        return savedTalk;
    }
}
