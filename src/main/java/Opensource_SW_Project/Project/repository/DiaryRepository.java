package Opensource_SW_Project.Project.repository;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByMember(Member member);
}
