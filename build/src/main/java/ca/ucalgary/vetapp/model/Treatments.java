package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table
public class Treatments {
    @Id
    @SequenceGenerator(name = "sequence_treatment", sequenceName = "sequence_treatment", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_treatment")
    @Column(name = "o_treatmentid")
    private long treatmentId;

    @Column(name = "o_treatmentdesc")
    private String treatmentDesc;

    @ManyToOne
    @JoinColumn(name = "o_animalid")
    private Animal theAnimal;

    @Column(name = "o_treatmentdate")
    private LocalDate treatmentDate;

    @ManyToOne
    @JoinColumn(name = "o_treatedby")
    private User treatedBy;

    public long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentDesc() {
        return treatmentDesc;
    }

    public void setTreatmentDesc(String treatmentDesc) {
        this.treatmentDesc = treatmentDesc;
    }

    public Animal getTheAnimal() {
        return theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public User getTreatedBy() {
        return treatedBy;
    }

    public void setTreatedBy(User treatedBy) {
        this.treatedBy = treatedBy;
    }
}
