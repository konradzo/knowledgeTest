package pl.kzochowski.knowledgeTest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.model.ExamList;
import pl.kzochowski.knowledgeTest.service.ExamService;
import pl.kzochowski.knowledgeTest.util.json.NewExamJson;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamEndpoint {
    private final ExamService examService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Exam create(@RequestBody NewExamJson json) {
        return examService.addNewExam(json);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Exam fetch(@PathVariable Integer id) {
        return examService.fetchExamById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ExamList listAll() {
        return examService.listAllExams();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Exam update(@PathVariable Integer id, @RequestBody @Valid Exam updatedExam) {
        return examService.updateExam(id, updatedExam);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@PathVariable Integer id) {
        examService.removeExam(id);
    }
}
