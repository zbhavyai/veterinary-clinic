package ca.ucalgary.vetapp.model;

import java.time.LocalDate;

public class AnimalIssue {
    private int issueId;
    private String issueDesc;
    private Animal theAnimal;
    private LocalDate detectedDate;
    private boolean resolved;

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
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

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
