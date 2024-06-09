package Opensource_SW_Project.Project.service.StyleService;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.domain.Style;
import Opensource_SW_Project.Project.repository.MemberRepository;
import Opensource_SW_Project.Project.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional   // 이게 주석처리되어 있으면 update 안먹음
@Slf4j
public class StyleCommandServiceImpl implements StyleCommandService {

    private final MemberRepository memberRepository;
    private final StyleRepository styleRepository;

    @Transactional
    public Style saveStyle(Long memberId, String content) {
        Member getMember = memberRepository.findById(memberId).get();
        Style newStyle = Style.builder()
                .member(getMember)
                .content(content)
                .build();

        Style saveStyle = styleRepository.save(newStyle);

        return styleRepository.save(newStyle);
    }

    @Override
    @Transactional
    public void deleteStyle(Long styleId) {
        // Check if style exists
        Style style = styleRepository.findById(styleId).orElse(null);
        if (style != null) {
            // Delete the style if it exists
            styleRepository.delete(style);
        }
    }
}
