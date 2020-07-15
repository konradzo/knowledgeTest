package pl.kzochowski.knowledgeTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne(mappedBy = "subscription", cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;

    @CreationTimestamp
    private LocalDateTime activeFrom;

    @NotNull
    private LocalDateTime activeUntil;


}
