package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * Issues with the animal
 */
@Entity
@Table(name = "issues")
public class Issues {
    /**
     * Unique Issue id
     */
    @Id
    @SequenceGenerator(name = "sequence_issues", sequenceName = "sequence_issues", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_issues")
    @Column(name = "i_issueid")
    private long issueId;

    /**
     * Text of the issue reported
     */
    @Column(name = "i_issuedesc")
    private String issueDesc;

    /**
     * The animal on which has the issue
     */
    @ManyToOne
    @JoinColumn(name = "i_animalid", foreignKey = @ForeignKey(name = "fk_i_animalid_issues"))
    private Animal theAnimal;

    /**
     * Date of detection of issue
     */
    @Column(name = "i_detecteddate")
    private LocalDate detectedDate;

    /**
     * User raising the issue
     */
    @ManyToOne
    @JoinColumn(name = "i_raisedby", foreignKey = @ForeignKey(name = "fk_i_raisedby_issues"), nullable = false)
    private User raisedBy;

    /**
     * Status of the issue
     */
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
        return this.raisedBy.getFullName();
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
