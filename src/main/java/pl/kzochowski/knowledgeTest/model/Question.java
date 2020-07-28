package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "possible_answers", joinColumns = @JoinColumn(name = "question_id"))
    private List<String> answers;

    @NotBlank
    private String correctAnswer;
}
