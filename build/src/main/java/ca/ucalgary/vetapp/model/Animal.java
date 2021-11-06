package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Animal {
    private int animalId;
    private String name;
    private String breed;
    private LocalDate birthDate; // if exact is unknown, put that returns most accurate age in months
    private String sex;
    private AnimalStatus status;
    private Owner theOwner;
    private int tattooNum;
    private String rfidNumber;
    private String microChipNumber;
    private HashMap<LocalDate, Double> weight; // weight along with record date
    private String coatColor;
    private String continuousMedication;

    private ArrayList<AnimalPhoto> animalPhotoList = new ArrayList<AnimalPhoto>();
    private ArrayList<AnimalTreatment> animalTreatmentList = new ArrayList<AnimalTreatment>();
    private ArrayList<AnimalIssue> animalIssueList = new ArrayList<AnimalIssue>();

    /**
     * Calculates and returns the age in months
     *
     * @return age in months
     */
    public int getAge() {
        // TODO: get current date, subtract birthdate, convert in months, return
        return 0;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
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

    public ArrayList<AnimalPhoto> getAnimalPhotoList() {
        return animalPhotoList;
    }

    public void setAnimalPhotoList(ArrayList<AnimalPhoto> animalPhotoList) {
        this.animalPhotoList = animalPhotoList;
    }

    public ArrayList<AnimalTreatment> getAnimalTreatmentList() {
        return animalTreatmentList;
    }

    public void setAnimalTreatmentList(ArrayList<AnimalTreatment> animalTreatmentList) {
        this.animalTreatmentList = animalTreatmentList;
    }

    public ArrayList<AnimalIssue> getAnimalIssueList() {
        return animalIssueList;
    }

    public void setAnimalIssueList(ArrayList<AnimalIssue> animalIssueList) {
        this.animalIssueList = animalIssueList;
    }
}
