package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table
public class Comments {
    @Id
    @SequenceGenerator(name = "sequence_comment", sequenceName = "sequence_comment", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_comment")
    @Column(name = "c_commentid")
    private long commentId;

    @Column(name = "c_commentdesc")
    private String commentDesc;

    @ManyToOne
    @JoinColumn(name = "c_animalid")
    private Animal theAnimal;

    @Column(name = "c_commentdate")
    private LocalDate commentDate;

    @ManyToOne
    @JoinColumn(name = "c_commenter")
    private User commenter;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    public Animal getTheAnimal() {
        return theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
}
