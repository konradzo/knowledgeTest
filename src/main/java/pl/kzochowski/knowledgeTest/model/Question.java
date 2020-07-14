package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
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

    @NotNull
    private String firstAnswer;

    @NotNull
    private String secondAnswer;

    @NotNull
    private String thirdAnswer;

    @NotNull
    private String fourthAnswer;

    @NotNull
    private String correctAnswer;
}
