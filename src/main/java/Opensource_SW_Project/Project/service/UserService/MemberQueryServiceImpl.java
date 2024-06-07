package Opensource_SW_Project.Project.service.UserService;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.repository.MemberRepository;
import Opensource_SW_Project.Project.web.dto.Member.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    @Override
    public Member getMember(MemberRequestDTO.signInRequestDTO request){
        return memberRepository.findByUsername(request.getUsername()).get();
    }
}
