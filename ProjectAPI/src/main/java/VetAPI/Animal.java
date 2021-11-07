package VetAPI;

import java.util.Date;

public class Animal {
	private int animalID, rfidNumber;
	private Date dob;
	private String name, breed, sex, status, oName,oContact,oEmail, oAddress, microchip, tattoo, coatColor;
	public Animal(int animalID, int rfidNumber, Date dob, String name, String breed, String sex, String status,
			String oName, String oContact, String oEmail, String oAddress, String microchip, String tattoo,
			String coatColor) {
		super();
		this.animalID = animalID;
		this.rfidNumber = rfidNumber;
		this.dob = dob;
		this.name = name;
		this.breed = breed;
		this.sex = sex;
		this.status = status;
		this.oName = oName;
		this.oContact = oContact;
		this.oEmail = oEmail;
		this.oAddress = oAddress;
		this.microchip = microchip;
		this.tattoo = tattoo;
		this.coatColor = coatColor;
	}


	private class Issue{
		private Date date;
		private String issueDetails;
		public Issue(Date date, String issueDetails) {
			super();
			this.date = date;
			this.issueDetails = issueDetails;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getIssueDetails() {
			return issueDetails;
		}
		public void setIssueDetails(String issueDetails) {
			this.issueDetails = issueDetails;
		}



	}

	private class Comment{
		private Date date;
		private String commentDetails;
		public Comment(Date date, String commentDetails) {
			super();
			this.date = date;
			this.commentDetails = commentDetails;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getCommentDetails() {
			return commentDetails;
		}
		public void setCommentDetails(String commentDetails) {
			this.commentDetails = commentDetails;
		}



	}

	private class Treatment{
		private Date date;
		private String treatmentDetails;
		public Treatment(Date date, String treatmentDetails) {
			super();
			this.date = date;
			this.treatmentDetails = treatmentDetails;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getTreatmentDetails() {
			return treatmentDetails;
		}
		public void setTreatmentDetails(String treatmentDetails) {
			this.treatmentDetails = treatmentDetails;
		}


	}



}
