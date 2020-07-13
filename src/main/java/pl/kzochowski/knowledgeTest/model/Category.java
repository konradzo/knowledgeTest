package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Exam> examList;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ExamApproach> examApproachList;

}
