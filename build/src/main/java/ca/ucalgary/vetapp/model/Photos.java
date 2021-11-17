package ca.ucalgary.vetapp.model;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photos {
    @Id
    @SequenceGenerator(name = "sequence_photos", sequenceName = "sequence_photos", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_photos")
    @Column(name = "p_photoid")
    private long photoId;

    @ManyToOne
    @JoinColumn(name = "p_animalid", foreignKey = @ForeignKey(name = "fk_p_animalid_photos"))
    private Animal theAnimal;

    @Column(name = "p_photolink")
    private String photoLink;

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public Animal getTheAnimal() {
        return theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
