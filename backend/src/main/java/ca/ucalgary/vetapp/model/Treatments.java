package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * Treatments administered to the animal
 */
@Entity
@Table(name = "treatments")
public class Treatments {
    /**
     * Unique Treatment id
     */
    @Id
    @SequenceGenerator(name = "sequence_treatments", sequenceName = "sequence_treatments", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_treatments")
    @Column(name = "t_treatmentid")
    private long treatmentId;

    /**
     * Description of the treatment
     */
    @Column(name = "t_treatmentdesc")
    private String treatmentDesc;

    /**
     * Drug name, if administered, during the treatment
     */
    @Column(name = "t_drugname")
    private String drugName;

    /**
     * Dosage of the drug
     */
    @Column(name = "t_drugdose")
    private String drugDose;

    /**
     * Delivery method or any specific instructions while administering the drug
     */
    @Column(name = "t_deliverymethod")
    private String deliveryMethod;

    /**
     * The animal on which treatment is done
     */
    @ManyToOne
    @JoinColumn(name = "t_animalid", foreignKey = @ForeignKey(name = "fk_t_animalid_treatments"))
    private Animal theAnimal;

    /**
     * Date of administering the treatment
     */
    @Column(name = "t_treatmentdate")
    private LocalDate treatmentDate;

    /**
     * User who treated the animal
     */
    @ManyToOne
    @JoinColumn(name = "t_treatedby", foreignKey = @ForeignKey(name = "fk_t_treatedby_treatments"))
    private User treatedBy;

    public long getTreatmentId() {
        return this.treatmentId;
    }

    public void setTreatmentId(long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentDesc() {
        return this.treatmentDesc;
    }

    public void setTreatmentDesc(String treatmentDesc) {
        this.treatmentDesc = treatmentDesc;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugDose() {
        return this.drugDose;
    }

    public void setDrugDose(String drugDose) {
        this.drugDose = drugDose;
    }

    public String getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Animal fetchTheAnimal() {
        return this.theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public LocalDate getTreatmentDate() {
        return this.treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public User fetchTreatedBy() {
        return this.treatedBy;
    }

    public String getTreaterName() {
        return this.treatedBy.getFullName();
    }

    public void setTreatedBy(User treatedBy) {
        this.treatedBy = treatedBy;
    }
}
