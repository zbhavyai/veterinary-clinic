package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Represents an animal
 */
@Entity
@Table(name = "animals")
public class Animal {
    /**
     * Unique Animal id
     */
    @Id
    @SequenceGenerator(name = "sequence_animals", sequenceName = "sequence_animals", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_animals")
    @Column(name = "a_animalid")
    private long animalId;

    /**
     * Name of the animal
     */
    @Column(name = "a_name")
    private String name;

    /**
     * Specie of the animal - eg dog, cat, cow, horse
     */
    @Column(name = "a_species")
    private String species;

    /**
     * Sub-Specie of the animal
     */
    @Column(name = "a_subspecies")
    private String subSpecies;

    /**
     * The breed of the animal - eg German Shephard dog
     */
    @Column(name = "a_breed")
    private String breed;

    /**
     * The type/purpose of the animal - eg cattle can be draught, dairy, or meat
     */
    @Column(name = "a_type")
    private String type;

    /**
     * Region of the animal
     */
    @Column(name = "a_region")
    private String region;

    /**
     * Sex of the animal
     */
    @Column(name = "a_sex")
    private AnimalSex sex;

    /**
     * Birthdate of the animal
     *
     * If exact is unknown, put that returns most accurate age in months
     */
    @Column(name = "a_birthdate")
    private LocalDate birthDate;

    /**
     * Status of the animal - GREEN, YELLOW, RED, or INACTIVE
     */
    @Column(name = "a_status")
    private AnimalStatus status;

    /**
     * Request status of the animal - AVAILABLE, REQUESTED, ACCEPT_BY_ADMIN, READY,
     * CANCEL, or REJECT
     */
    @Column(name = "a_requeststatus")
    private AnimalRequestStatus requestStatus;

    /**
     * The owner of the animal
     */
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "a_ownerid", foreignKey = @ForeignKey(name = "fk_a_ownerid_animals"))
    private Owner theOwner;

    /**
     * Profile pic of the animal
     */
    @OneToOne
    @JoinColumn(name = "a_profilepic", foreignKey = @ForeignKey(name = "fk_a_profilepic_animals"))
    private Photos profilePic;

    /**
     * Tattoo number on the animal - eg 564543
     */
    @Column(name = "a_tattoonum")
    private int tattooNum;

    /**
     * City Tattoo on the animal - eg "HOC London"
     */
    @Column(name = "a_citytattoo")
    private String cityTattoo;

    /**
     * RFID tag number of the animal
     */
    @Column(name = "a_rfidnumber")
    private String rfidNumber;

    /**
     * Microchip number of the animal
     */
    @Column(name = "a_microchipnumber")
    private String microChipNumber;

    /**
     * Color of the animal
     */
    @Column(name = "a_coatcolor")
    private String coatColor;

    /**
     * If animal is on some continuous medication
     */
    @Column(name = "a_continuousmedication")
    private String continuousMedication;

    /**
     * A distinct feature of the animal for easy identification
     */
    @Column(name = "a_distinctfeature")
    private String distinctFeature;

    /**
     * Recorded weight history of the animal
     */
    @OneToMany(mappedBy = "theAnimal")
    private List<Weights> animalWeightList;

    /**
     * Photos of the animal
     */
    @OneToMany(mappedBy = "theAnimal")
    private List<Photos> animalPhotoList;

    /**
     * Treatments administered to the animal
     */
    @OneToMany(mappedBy = "theAnimal")
    private List<Treatments> animalTreatmentList;

    /**
     * Issues with the animal
     */
    @OneToMany(mappedBy = "theAnimal")
    private List<Issues> animalIssueList;

    /**
     * Comments made on the animal
     */
    @OneToMany(mappedBy = "theAnimal")
    private List<Comments> animalCommentList;

    private Animal() {
        this.animalWeightList = new ArrayList<>();
        this.animalPhotoList = new ArrayList<>();
        this.animalTreatmentList = new ArrayList<>();
        this.animalIssueList = new ArrayList<>();
        this.animalCommentList = new ArrayList<>();
    }

    public long getAnimalId() {
        return this.animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSubSpecies() {
        return this.subSpecies;
    }

    public void setSubSpecies(String subSpecies) {
        this.subSpecies = subSpecies;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public AnimalSex getSex() {
        return this.sex;
    }

    public void setSex(AnimalSex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Calculates and returns the age in months
     *
     * @return age in months
     */
    public int getAge() {
        if (this.birthDate == null) {
            return 0;
        }

        else {
            long months = Period.between(this.birthDate, LocalDate.now()).toTotalMonths();
            return ((int) months);
        }
    }

    public AnimalStatus getStatus() {
        return this.status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public AnimalRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(AnimalRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Owner fetchTheOwner() {
        return this.theOwner;
    }

    public long getOwnerId() {
        return this.theOwner.getOwnerId();
    }

    public String getOwnerName() {
        return this.theOwner.getFullName();
    }

    public String getOwnerContact() {
        return this.theOwner.getContactNumber();
    }

    public String getOwnerEmail() {
        return this.theOwner.getEmailId();
    }

    public String getOwnerAddress() {
        return this.theOwner.getAddress();
    }

    public void setTheOwner(Owner theOwner) {
        this.theOwner = theOwner;
    }

    public Photos fetchProfilePic() {
        return this.profilePic;
    }

    public void setProfilePic(Photos profilePic) {
        this.profilePic = profilePic;
    }

    public int getTattooNum() {
        return this.tattooNum;
    }

    public void setTattooNum(int tattooNum) {
        this.tattooNum = tattooNum;
    }

    public String getCityTattoo() {
        return this.cityTattoo;
    }

    public void setCityTattoo(String cityTattoo) {
        this.cityTattoo = cityTattoo;
    }

    public String getRfidNumber() {
        return this.rfidNumber;
    }

    public void setRfidNumber(String rfidNumber) {
        this.rfidNumber = rfidNumber;
    }

    public String getMicroChipNumber() {
        return this.microChipNumber;
    }

    public void setMicroChipNumber(String microChipNumber) {
        this.microChipNumber = microChipNumber;
    }

    public String getCoatColor() {
        return this.coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getContinuousMedication() {
        return this.continuousMedication;
    }

    public void setContinuousMedication(String continuousMedication) {
        this.continuousMedication = continuousMedication;
    }

    public String getDistinctFeature() {
        return this.distinctFeature;
    }

    public void setDistinctFeature(String distinctFeature) {
        this.distinctFeature = distinctFeature;
    }

    public List<Weights> fetchAnimalWeightList() {
        return this.animalWeightList;
    }

    public void setAnimalWeightList(List<Weights> animalWeightList) {
        this.animalWeightList = animalWeightList;
    }

    public List<Photos> fetchAnimalPhotoList() {
        return this.animalPhotoList;
    }

    public void setAnimalPhotoList(List<Photos> animalPhotoList) {
        this.animalPhotoList = animalPhotoList;
    }

    public List<Treatments> fetchAnimalTreatmentList() {
        return this.animalTreatmentList;
    }

    public void setAnimalTreatmentList(List<Treatments> animalTreatmentList) {
        this.animalTreatmentList = animalTreatmentList;
    }

    public List<Issues> fetchAnimalIssueList() {
        return this.animalIssueList;
    }

    public void setAnimalIssueList(List<Issues> animalIssueList) {
        this.animalIssueList = animalIssueList;
    }

    public List<Comments> fetchAnimalCommentList() {
        return this.animalCommentList;
    }

    public void setAnimalCommentList(List<Comments> animalCommentList) {
        this.animalCommentList = animalCommentList;
    }
}
