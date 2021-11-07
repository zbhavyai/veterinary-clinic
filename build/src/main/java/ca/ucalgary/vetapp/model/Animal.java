package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Animal {
    @Id
    @SequenceGenerator(name = "sequence_animal", sequenceName = "sequence_animal", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_animal")
    @Column(name = "a_animalid")
    private long animalId;

    @Column(name = "a_name")
    private String name;

    @Column(name = "a_breed")
    private String breed;

    @Column(name = "a_birthdate")
    private LocalDate birthDate; // if exact is unknown, put that returns most accurate age in months

    @Column(name = "a_sex")
    private String sex;

    @Column(name = "a_status")
    private AnimalStatus status;

    @ManyToOne
    // @ForeignKey(name = "")
    @JoinColumn(name = "a_ownerid")
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

    private Animal() {
        this.animalPhotoList = new ArrayList<>();
        this.animalTreatmentList = new ArrayList<>();
        this.animalIssueList = new ArrayList<>();
    }

    public Animal(String name, String breed, LocalDate birthDate, String sex, AnimalStatus status, Owner theOwner,
            int tattooNum, String rfidNumber, String microChipNumber, HashMap<LocalDate, Double> weight,
            String coatColor, String continuousMedication, List<Photos> animalPhotoList,
            List<Treatments> animalTreatmentList, List<Issues> animalIssueList) {
        this();
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.sex = sex;
        this.status = status;
        this.theOwner = theOwner;
        this.tattooNum = tattooNum;
        this.rfidNumber = rfidNumber;
        this.microChipNumber = microChipNumber;
        this.weight = weight;
        this.coatColor = coatColor;
        this.continuousMedication = continuousMedication;
        this.animalPhotoList = animalPhotoList;
        this.animalTreatmentList = animalTreatmentList;
        this.animalIssueList = animalIssueList;
    }

    /**
     * Calculates and returns the age in months
     *
     * @return age in months
     */
    public int getAge() {
        // TODO: get current date, subtract birthdate, convert in months, return
        return 0;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public AnimalStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalStatus status) {
        this.status = status;
    }

    public Owner getTheOwner() {
        return theOwner;
    }

    public void setTheOwner(Owner theOwner) {
        this.theOwner = theOwner;
    }

    public int getTattooNum() {
        return tattooNum;
    }

    public void setTattooNum(int tattooNum) {
        this.tattooNum = tattooNum;
    }

    public String getRfidNumber() {
        return rfidNumber;
    }

    public void setRfidNumber(String rfidNumber) {
        this.rfidNumber = rfidNumber;
    }

    public String getMicroChipNumber() {
        return microChipNumber;
    }

    public void setMicroChipNumber(String microChipNumber) {
        this.microChipNumber = microChipNumber;
    }

    public HashMap<LocalDate, Double> getWeight() {
        return weight;
    }

    public void setWeight(HashMap<LocalDate, Double> weight) {
        this.weight = weight;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getContinuousMedication() {
        return continuousMedication;
    }

    public void setContinuousMedication(String continuousMedication) {
        this.continuousMedication = continuousMedication;
    }

    public List<Photos> getAnimalPhotoList() {
        return animalPhotoList;
    }

    public void setAnimalPhotoList(ArrayList<Photos> animalPhotoList) {
        this.animalPhotoList = animalPhotoList;
    }

    public List<Treatments> getAnimalTreatmentList() {
        return animalTreatmentList;
    }

    public void setAnimalTreatmentList(ArrayList<Treatments> animalTreatmentList) {
        this.animalTreatmentList = animalTreatmentList;
    }

    public List<Issues> getAnimalIssueList() {
        return animalIssueList;
    }

    public void setAnimalIssueList(ArrayList<Issues> animalIssueList) {
        this.animalIssueList = animalIssueList;
    }
}
