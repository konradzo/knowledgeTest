package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @NotBlank
    private String firstAnswer;

    @NotBlank
    private String secondAnswer;

    @NotBlank
    private String thirdAnswer;

    @NotBlank
    private String fourthAnswer;

    @NotBlank
    private String correctAnswer;
}
