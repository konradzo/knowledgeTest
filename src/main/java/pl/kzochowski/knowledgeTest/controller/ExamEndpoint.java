package pl.kzochowski.knowledgeTest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.model.ExamList;
import pl.kzochowski.knowledgeTest.service.ExamService;
import pl.kzochowski.knowledgeTest.util.json.NewExamJson;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamEndpoint {
    private final ExamService examService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Exam createExam(@RequestBody NewExamJson json) {
        return examService.addNewExam(json);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ExamList listExams() {
        List<Exam> exams = examService.listAllExams();
        log.info("Exams list size: {}", exams.size());
        return new ExamList(exams);
    }
}
