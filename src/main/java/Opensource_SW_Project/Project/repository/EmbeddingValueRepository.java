package Opensource_SW_Project.Project.repository;

import Opensource_SW_Project.Project.domain.Diary;
import Opensource_SW_Project.Project.domain.EmbeddingValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmbeddingValueRepository  extends JpaRepository<EmbeddingValue, Long> {
    List<EmbeddingValue> findAll();
}
