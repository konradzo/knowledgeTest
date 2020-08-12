package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.Exam;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
}
