package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.repository.ExamRepository;
import pl.kzochowski.knowledgeTest.service.ExamService;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public Exam addNewExam(Exam exam) {
        return examRepository.save(exam);
    }
}
