package pl.kzochowski.knowledgeTest.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne(mappedBy = "subscription", cascade = CascadeType.ALL)
    private User user;

    @NotNull
    @CreationTimestamp
    private LocalDateTime activeFrom;

    @NotNull
    private LocalDateTime activeUntil;
}
