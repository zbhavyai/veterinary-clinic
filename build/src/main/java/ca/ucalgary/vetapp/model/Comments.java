package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * Comments made on the animal
 */
@Entity
@Table(name = "comments")
public class Comments {
    /**
     * Unique Comment id
     */
    @Id
    @SequenceGenerator(name = "sequence_comments", sequenceName = "sequence_comments", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_comments")
    @Column(name = "c_commentid")
    private long commentId;

    /**
     * Text of the comment made
     */
    @Column(name = "c_commentdesc")
    private String commentDesc;

    /**
     * The animal on which comment is made
     */
    @ManyToOne
    @JoinColumn(name = "c_animalid", foreignKey = @ForeignKey(name = "fk_c_animalid_comments"))
    private Animal theAnimal;

    /**
     * Date of adding comment
     */
    @Column(name = "c_commentdate")
    private LocalDate commentDate;

    /**
     * User making the comment
     */
    @ManyToOne
    @JoinColumn(name = "c_commenter", foreignKey = @ForeignKey(name = "fk_c_commenter_comments"))
    private User commenter;

    public long getCommentId() {
        return this.commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentDesc() {
        return this.commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    public Animal fetchTheAnimal() {
        return this.theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public LocalDate getCommentDate() {
        return this.commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public User fetchCommenter() {
        return this.commenter;
    }

    public String getCommenterName() {
        return this.commenter.getFullName();
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
}
