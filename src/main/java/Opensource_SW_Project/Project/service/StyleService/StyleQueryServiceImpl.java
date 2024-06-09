package Opensource_SW_Project.Project.service.StyleService;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.domain.Style;
import Opensource_SW_Project.Project.repository.MemberRepository;
import Opensource_SW_Project.Project.repository.StyleRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class StyleQueryServiceImpl implements StyleQueryService{

    private final StyleRepository styleRepository;
    private final MemberRepository memberRepository;

//    @Override
//    public List<Style> getUserStyle(Long memberId) {
//        Member getMember = memberRepository.findById(memberId).get();
//        List<Style> UserStyleList = styleRepository.findAllByMember(getMember);
//
//        return UserStyleList;
//    }

    @Override
    public Style getUserStyle(Long memberId) {
        Member getMember = memberRepository.findById(memberId).get();
        Style UserStyle = styleRepository.findByMember(getMember);
        return UserStyle;
    }

    @Override
    public Style getUserLastStyle(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return styleRepository.findByMember(member);
        } else {
            throw new EntityNotFoundException("Member with id " + memberId + " not found.");
        }
    }

}
