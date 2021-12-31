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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Inspection getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(Inspection deficiency) {
        this.deficiency = deficiency;
    }
}
