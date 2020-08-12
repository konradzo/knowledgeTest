package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.util.json.NewExamJson;

import java.util.List;

public interface ExamService {
    Exam addNewExam(NewExamJson json);
    List<Exam> listAllExams();
}
