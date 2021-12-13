package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * Recorded weight of the animal
 */
@Entity
@Table(name = "weights")
public class Weights {
    /**
     * Unique Weight id
     */
    @Id
    @SequenceGenerator(name = "sequence_weights", sequenceName = "sequence_weights", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_weights")
    @Column(name = "w_weightid")
    private long weightId;

    /**
     * The weight recorded in Kg
     */
    @Column(name = "w_massinkg", nullable = false)
    private Double massInKg;

    /**
     * The animal whose weight is being recorded
     */
    @ManyToOne
    @JoinColumn(name = "w_animalid", foreignKey = @ForeignKey(name = "fk_w_animalid_weights"), nullable = false)
    private Animal theAnimal;

    /**
     * Record date of the weight
     */
    @Column(name = "w_recorddate", nullable = false)
    private LocalDate recordDate;

    /**
     * The user recording the weight
     */
    @OneToOne
    @JoinColumn(name = "w_recordedby", foreignKey = @ForeignKey(name = "fk_w_recordedby_weights"), nullable = false)
    private User recordedBy;

    public long getWeightId() {
        return this.weightId;
    }

    public void setWeightId(long weightId) {
        this.weightId = weightId;
    }

    public Animal fetchTheAnimal() {
        return this.theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public LocalDate getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Double getMassInKg() {
        return this.massInKg;
    }

    public void setMassInKg(Double massInKg) {
        this.massInKg = massInKg;
    }

    public Double getMassinPounds() {
        return this.massInKg * 0.45_359_237;
    }

    public User fetchRecordedBy() {
        return this.recordedBy;
    }

    public void setRecordedBy(User recordedBy) {
        this.recordedBy = recordedBy;
    }
}
