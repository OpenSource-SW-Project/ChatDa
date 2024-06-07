package Opensource_SW_Project.Project.service.UserService;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.web.dto.JwtToken;
import Opensource_SW_Project.Project.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {
    Member signUp(MemberRequestDTO.CreateUserRequestDTO request);

    JwtToken signIn(String username, String password);
}
