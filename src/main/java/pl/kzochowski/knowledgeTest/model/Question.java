package pl.kzochowski.knowledgeTest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    //todo enum?
    @NotBlank
    private String correctAnswer;
}
