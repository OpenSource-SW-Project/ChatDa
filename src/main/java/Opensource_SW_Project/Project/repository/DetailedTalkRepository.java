package Opensource_SW_Project.Project.repository;

import Opensource_SW_Project.Project.domain.DetailedTalk;
import Opensource_SW_Project.Project.domain.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailedTalkRepository extends JpaRepository<DetailedTalk, Long> {
    //List<Talk> findByTalkId(Long talkId);
}
