package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;
import java.nio.file.*;

/**
 * Photos of the animal
 */
@Entity
@Table(name = "photos")
public class Photos {
    public static Path storeLocation = Paths.get("photos");

    /**
     * Unique Photo id
     */
    @Id
    @SequenceGenerator(name = "sequence_photos", sequenceName = "sequence_photos", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_photos")
    @Column(name = "p_photoid")
    private long photoId;

    /**
     * Optional description of the photo
     */
    @Column(name = "p_photodesc")
    private String photoDesc;

    /**
     * The animal whose photo it is
     */
    @ManyToOne
    @JoinColumn(name = "p_animalid", foreignKey = @ForeignKey(name = "fk_p_animalid_photos"))
    private Animal theAnimal;

    /**
     * The location/url of the photo
     */
    @Column(name = "p_photolink")
    private String photoLink;

    /**
     * Alternate text of the photo
     */
    @Column(name = "p_alttext")
    private String altText;

    /**
     * The user uploading the photo
     */
    @OneToOne
    @JoinColumn(name = "p_uploader", foreignKey = @ForeignKey(name = "fk_p_uploader_photos"), nullable = false)
    private User uploader;

    /**
     * Photo upload date
     */
    @Column(name = "p_uploaddate", nullable = false)
    private LocalDate uploadDate;

    public long getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoDesc() {
        return this.photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public Animal fetchTheAnimal() {
        return this.theAnimal;
    }

    public void setTheAnimal(Animal theAnimal) {
        this.theAnimal = theAnimal;
    }

    public String getPhotoLink() {
        return this.photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getAltText() {
        return this.altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public User fetchUploader() {
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public LocalDate getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
