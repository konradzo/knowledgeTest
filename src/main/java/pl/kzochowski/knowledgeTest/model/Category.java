package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Exam> examList;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ExamApproach> examApproachList;
}
