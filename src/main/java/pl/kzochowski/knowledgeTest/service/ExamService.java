package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.model.ExamList;
import pl.kzochowski.knowledgeTest.util.json.NewExamJson;

public interface ExamService {
    Exam addNewExam(NewExamJson json);

    ExamList listAllExams();

    class ExamDoesNotExistException extends RuntimeException {
        public ExamDoesNotExistException(String header) {
            super(String.format("Exam with title %s does not exist", header));
        }
    }

    class ExamAlreadyExistsException extends RuntimeException {
        public ExamAlreadyExistsException(String header) {
            super(String.format("Exam with title %s already exists!", header));
        }
    }


}
