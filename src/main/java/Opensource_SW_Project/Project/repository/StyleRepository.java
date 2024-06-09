package Opensource_SW_Project.Project.repository;

import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.domain.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    List<Style> findAllByMember(Member member);

    Style findByMember(Member member);
}