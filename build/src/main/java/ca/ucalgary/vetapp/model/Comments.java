package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @SequenceGenerator(name = "sequence_comments", sequenceName = "sequence_comments", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_comments")
    @Column(name = "c_commentid")
    private long commentId;

    @Column(name = "c_commentdesc")
    private String commentDesc;

    @ManyToOne
    @JoinColumn(name = "c_animalid", foreignKey = @ForeignKey(name = "fk_c_animalid_comments"))
    private Animal theAnimal;

    @Column(name = "c_commentdate")
    private LocalDate commentDate;

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
        String fname = this.commenter.getFirstName();
        String mname = this.commenter.getMiddleName();
        String lname = this.commenter.getLastName();

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

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
}
