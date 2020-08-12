package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.Exam;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
    List<Exam> findAll();
}
