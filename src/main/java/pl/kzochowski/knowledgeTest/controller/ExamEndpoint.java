package pl.kzochowski.knowledgeTest.controller;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.service.ExamService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamEndpoint {
    private final ExamService examService;

    @PostMapping
    Exam createExam(@RequestBody @Valid Exam newExam){
        return examService.addNewExam(newExam);
    }
}
