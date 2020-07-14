package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String header;

    @NotBlank
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Question> questionList;
}
