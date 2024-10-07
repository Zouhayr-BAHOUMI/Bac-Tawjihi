package tawjih.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tawjih.enums.Domain;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contenuQuestion", nullable = false)
    private String contenuQuestion;

    @Enumerated(EnumType.STRING)
    private Domain domain;

    @OneToMany(mappedBy = "question" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Choix> choix;

    @ManyToOne
    @JoinColumn(name = "id_test")
    @JsonBackReference
    private Test test;
}
