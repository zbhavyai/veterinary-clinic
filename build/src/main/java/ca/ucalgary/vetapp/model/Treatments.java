package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "treatments")
public class Treatments {
    @Id
    @SequenceGenerator(name = "sequence_treatments", sequenceName = "sequence_treatments", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_treatments")
    @Column(name = "t_treatmentid")
    private long treatmentId;

    @Column(name = "t_treatmentdesc")
    private String treatmentDesc;

    @ManyToOne
    @JoinColumn(name = "t_animalid", foreignKey = @ForeignKey(name = "fk_t_animalid_treatments"))
    private Animal theAnimal;

    @Column(name = "t_treatmentdate")
    private LocalDate treatmentDate;

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
        String fname = this.treatedBy.getFirstName();
        String mname = this.treatedBy.getMiddleName();
        String lname = this.treatedBy.getLastName();

        if(fname == null) {
            fname = "";
        }

        if(mname == null) {
            mname = "";
        }

        if(lname == null) {
            lname = "";
        }

        String name = String.format("%s %s %s", fname, mname, lname);
        return name;
    }

    public void setTreatedBy(User treatedBy) {
        this.treatedBy = treatedBy;
    }
}
