package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Animal {
	private int animalId;
	private String Name;
	private String status;
	private int tattooNum;
	private String cityTattoo;
	private int weight;
	private int age; //in months
	private LocalDate birthDate;
	private String breed;
	private String sex;
	private String coatColor;
	private ArrayList<AnimalPhoto> animalPhotoList = new ArrayList<AnimalPhoto>();
	private ArrayList<AnimalTreatment> animalTreatmentList = new ArrayList<AnimalTreatment>();
	private ArrayList<AnimalIssue> animalIssueList = new ArrayList<AnimalIssue>();




}
