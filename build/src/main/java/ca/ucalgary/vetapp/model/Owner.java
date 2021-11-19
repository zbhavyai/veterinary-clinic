package ca.ucalgary.vetapp.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @SequenceGenerator(name = "sequence_owners", sequenceName = "sequence_owners", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_owners")
    @Column(name = "o_ownerid")
    private long ownerId;

    @OneToMany(mappedBy = "theOwner")
    private List<Animal> animalList;

    @Column(name = "o_firstname")
    private String firstName;

    @Column(name = "o_middlename")
    private String middleName;

    @Column(name = "o_lastname")
    private String lastName;

    @Column(name = "o_role")
    private UserRole role;

    @Column(name = "o_contactnumber")
    private String contactNumber;

    @Column(name = "o_emailid")
    private String emailId;

    @Column(name = "o_address")
    private String address;

    public long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Animal> fetchOwnedAnimals() {
        return this.animalList;
    }

    public void setOwnedAnimals(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
