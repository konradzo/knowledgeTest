package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //todo cascading
    @NotNull
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    //todo how to handle it?
//    private Map<String,String> answers;

    @NotNull
    private String correctAnswer;
}
