package Opensource_SW_Project.Project.service.UserService;

import Opensource_SW_Project.Project.JWT.JwtTokenProvider;
import Opensource_SW_Project.Project.converter.UserConverter;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.repository.MemberRepository;
import Opensource_SW_Project.Project.web.dto.JwtToken;
import Opensource_SW_Project.Project.web.dto.Member.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Member signUp(MemberRequestDTO.CreateUserRequestDTO request){
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        // Password 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member newMember = UserConverter.toMember(request, encodedPassword, roles);

        return memberRepository.save(newMember);
    }


    @Transactional
    @Override
    public JwtToken signIn(String username, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        Member getMember = memberRepository.findByUsername(username).get();
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication, getMember.getUserId());

        return jwtToken;
    }
}
