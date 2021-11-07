package ca.ucalgary.vetapp.model;

import javax.persistence.*;

@Entity
@Table
public class Photos {
    @Id
    @SequenceGenerator(name = "sequence_photo", sequenceName = "sequence_photo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_photo")
    @Column(name = "p_photoid")
    private long photoId;

    @ManyToOne
    @JoinColumn(name = "p_animalid")
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
