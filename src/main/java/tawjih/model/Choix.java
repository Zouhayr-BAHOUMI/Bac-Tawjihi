package tawjih.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "choix")
public class Choix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contenuChoix", nullable = false)
    private String contenuChoix;

    @Column(name = "isCorrect", nullable = false)
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;
}
