package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "issues")
public class Issues {
    @Id
    @SequenceGenerator(name = "sequence_issues", sequenceName = "sequence_issues", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_issues")
    @Column(name = "i_issueid")
    private long issueId;

    @Column(name = "i_issuedesc")
    private String issueDesc;

    @ManyToOne
    @JoinColumn(name = "i_animalid", foreignKey = @ForeignKey(name = "fk_i_animalid_issues"))
    private Animal theAnimal;

    @Column(name = "i_detecteddate")
    private LocalDate detectedDate;

    @ManyToOne
    @JoinColumn(name = "i_raisedby", foreignKey = @ForeignKey(name = "fk_i_raisedby_issues"))
    private User raisedBy;

    @Column(name = "i_isresolved")
    private boolean isResolved;

    public long getIssueId() {
        return this.issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getIssueDesc() {
        return this.issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public Animal fetchTheAnimal() {
        return this.theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public LocalDate getDetectedDate() {
        return this.detectedDate;
    }

    public void setDetectedDate(LocalDate detectedDate) {
        this.detectedDate = detectedDate;
    }

    public User fetchRaisedBy() {
        return this.raisedBy;
    }

    public String getIssueRaiserName() {
        String fname = this.raisedBy.getFirstName();
        String mname = this.raisedBy.getMiddleName();
        String lname = this.raisedBy.getLastName();

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

    public void setRaisedBy(User raisedBy) {
        this.raisedBy = raisedBy;
    }

    public boolean isResolved() {
        return this.isResolved;
    }

    public void setResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }
}
