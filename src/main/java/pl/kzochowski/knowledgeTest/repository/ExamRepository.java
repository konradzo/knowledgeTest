package pl.kzochowski.knowledgeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kzochowski.knowledgeTest.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
    List<Exam> findAll();

    Optional<Exam> findByHeaderAndCategory_Id(String header, Integer id);
}
