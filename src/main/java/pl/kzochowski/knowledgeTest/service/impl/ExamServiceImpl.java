package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.repository.CategoryRepository;
import pl.kzochowski.knowledgeTest.repository.ExamRepository;
import pl.kzochowski.knowledgeTest.service.CategoryService;
import pl.kzochowski.knowledgeTest.service.ExamService;
import pl.kzochowski.knowledgeTest.util.json.NewExamJson;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Exam addNewExam(NewExamJson json) {
        Optional<Category> category = categoryRepository.findById(json.getCategoryId());
        if (!category.isPresent())
            throw new CategoryService.CategoryDoesNotExistException(json.getCategoryId());

        Exam exam = new Exam();
        exam.setCategory(category.get());
        exam.setHeader(json.getHeader());
        exam.setDescription(json.getDescription());

        return examRepository.save(exam);
    }

    @Override
    public List<Exam> listAllExams() {
        return examRepository.findAll();
    }
}
