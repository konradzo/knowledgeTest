package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.ExamApproach;

import java.util.List;

public interface ExamApproachRepository extends JpaRepository<ExamApproach, Integer> {

    List<ExamApproach> findAllByUser_Id(Integer id);

}
