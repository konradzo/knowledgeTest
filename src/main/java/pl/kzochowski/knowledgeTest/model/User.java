package pl.kzochowski.knowledgeTest.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String email;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExamApproach> examApproachList;
}
