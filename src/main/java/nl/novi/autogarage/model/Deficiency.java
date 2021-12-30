package nl.novi.autogarage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "deficiencies")
public class Deficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @JsonIgnoreProperties("deficiencies")
    @ManyToOne
    @JoinColumn(name = "inspection_id", referencedColumnName = "id")
    private Inspection deficiency;



}
