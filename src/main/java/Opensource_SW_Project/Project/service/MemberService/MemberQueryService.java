package Opensource_SW_Project.Project.service.MemberService;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.web.dto.Member.MemberRequestDTO;

public interface MemberQueryService {
    Member getMember(MemberRequestDTO.signInRequestDTO request);
}
