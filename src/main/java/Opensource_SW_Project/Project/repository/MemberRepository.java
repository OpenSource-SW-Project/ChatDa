package Opensource_SW_Project.Project.repository;

import Opensource_SW_Project.Project.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);                      // 회원이 저장소에 저장
    Optional<Member> findById(Long id);              // 가져와야 되는데 null값인 경우 그대로 반환하는 것이 아닌 Optional로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
