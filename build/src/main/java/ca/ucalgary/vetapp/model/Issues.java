package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table
public class Issues {
    @Id
    @SequenceGenerator(name = "sequence_issue", sequenceName = "sequence_issue", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_issue")
    private long issueId;
    private String issueDesc;

    @ManyToOne
    private Animal theAnimal;
    private LocalDate detectedDate;

    @ManyToOne
    private User raisedBy;
    private boolean resolved;

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getIssueDesc() {
        return issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public Animal getTheAnimal() {
    return theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
    this.theAnimal = theAnimal;
    }

    public LocalDate getDetectedDate() {
        return detectedDate;
    }

    public void setDetectedDate(LocalDate detectedDate) {
        this.detectedDate = detectedDate;
    }

    public User getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(User raisedBy) {
        this.raisedBy = raisedBy;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}