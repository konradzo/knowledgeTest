package pl.kzochowski.knowledgeTest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
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
