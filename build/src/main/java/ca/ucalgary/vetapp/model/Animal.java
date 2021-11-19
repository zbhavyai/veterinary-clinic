package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @SequenceGenerator(name = "sequence_animals", sequenceName = "sequence_animals", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_animals")
    @Column(name = "a_animalid")
    private long animalId;

    @Column(name = "a_name")
    private String name;

    @Column(name = "a_type")
    private String type;

    @Column(name = "a_breed")
    private String breed;

    @Column(name = "a_birthdate")
    private LocalDate birthDate; // if exact is unknown, put that returns most accurate age in months

    @Column(name = "a_sex")
    private String sex;

    @Column(name = "a_status")
    private AnimalStatus status;

    @ManyToOne
    @JoinColumn(name = "a_ownerid", foreignKey = @ForeignKey(name = "fk_a_ownerid_animals"))
    private Owner theOwner;

    @Column(name = "a_tattoonum")
    private int tattooNum;

    @Column(name = "a_rfidnumber")
    private String rfidNumber;

    @Column(name = "a_microchipnumber")
    private String microChipNumber;

    @Column(name = "a_weight")
    private HashMap<LocalDate, Double> weight; // weight along with record date

    @Column(name = "a_coatcolor")
    private String coatColor;

    @Column(name = "a_continuousmedication")
    private String continuousMedication;

    @OneToMany(mappedBy = "theAnimal")
    private List<Photos> animalPhotoList = new ArrayList<Photos>();

    @OneToMany(mappedBy = "theAnimal")
    private List<Treatments> animalTreatmentList = new ArrayList<Treatments>();

    @OneToMany(mappedBy = "theAnimal")
    private List<Issues> animalIssueList = new ArrayList<Issues>();

    @OneToMany(mappedBy = "theAnimal")
    private List<Comments> animalCommentList = new ArrayList<Comments>();

    private Animal() {
        this.animalPhotoList = new ArrayList<>();
        this.animalTreatmentList = new ArrayList<>();
        this.animalIssueList = new ArrayList<>();
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public AnimalStatus getStatus() {
        return this.status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public Owner fetchTheOwner() {
        return this.theOwner;
    }

    public long getOwnerId() {
        return this.theOwner.getOwnerId();
    }

    public String getOwnerName() {
        String fname = this.theOwner.getFirstName();
        String mname = this.theOwner.getMiddleName();
        String lname = this.theOwner.getLastName();

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

    public int getTattooNum() {
        return this.tattooNum;
    }

    public void setTattooNum(int tattooNum) {
        this.tattooNum = tattooNum;
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

    public HashMap<LocalDate, Double> getWeight() {
        return this.weight;
    }

    public void setWeight(HashMap<LocalDate, Double> weight) {
        this.weight = weight;
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
