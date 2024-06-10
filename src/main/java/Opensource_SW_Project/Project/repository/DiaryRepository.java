package Opensource_SW_Project.Project.repository;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.Member;
import Opensource_SW_Project.Project.domain.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Diary findByMember(Member member);
    List<Diary> findByTalk(Talk talk);
    List<Diary> findAllByMember(Member member);
}
