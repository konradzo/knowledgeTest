package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.model.ExamList;
import pl.kzochowski.knowledgeTest.repository.CategoryRepository;
import pl.kzochowski.knowledgeTest.repository.ExamRepository;
import pl.kzochowski.knowledgeTest.service.CategoryService;
import pl.kzochowski.knowledgeTest.service.ExamService;
import pl.kzochowski.knowledgeTest.util.json.NewExamJson;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Exam addNewExam(NewExamJson json) {
        Optional<Category> category = categoryRepository.findById(json.getCategoryId());
        if (!category.isPresent()) {
            throw new CategoryService.CategoryDoesNotExistException(json.getCategoryId());
        }

        Optional<Exam> exam = examRepository.findByHeaderAndCategory_Id(json.getHeader(), json.getCategoryId());
        if (exam.isPresent()) {
            throw new ExamAlreadyExistsException(json.getHeader());
        }

        Exam newExam = new Exam();
        newExam.setCategory(category.get());
        newExam.setHeader(json.getHeader());
        newExam.setDescription(json.getDescription());
        return examRepository.save(newExam);
    }

    @Override
    public Exam fetchExamById(Integer id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (!exam.isPresent())
            throw new ExamDoesNotExistException(id);
        log.info("Fetched exam: {}", exam.get().getHeader());
        return exam.get();
    }

    @Override
    public Exam updateExam(Integer id, Exam updatedExam) {
        Optional<Exam> exam = examRepository.findById(id);
        if (!exam.isPresent())
            throw new ExamDoesNotExistException(id);

        examRepository.save(updatedExam);
        log.info("Exam with id {} updated", id);
        return updatedExam;
    }

    @Override
    public ExamList listAllExams() {
        List<Exam> exams = examRepository.findAll();
        log.info("Found exam list size: {}", exams.size());
        return new ExamList(exams.size(), exams);
    }
}
