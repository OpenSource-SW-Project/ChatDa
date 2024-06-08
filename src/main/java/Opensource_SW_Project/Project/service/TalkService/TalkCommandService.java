package Opensource_SW_Project.Project.service.TalkService;

import Opensource_SW_Project.Project.domain.Talk;

public interface TalkCommandService {
    Talk createTalk(Long memberId);
}
