package ca.ucalgary.vetapp.model;

import java.util.List;
import javax.persistence.*;

/**
 * Owner of the animal
 */
@Entity
@Table(name = "owners")
public class Owner {
    /**
     * Unique Owner id
     */
    @Id
    @SequenceGenerator(name = "sequence_owners", sequenceName = "sequence_owners", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_owners")
    @Column(name = "o_ownerid")
    private long ownerId;

    /**
     * Animals owned by the owner
     */
    @OneToMany(mappedBy = "theOwner")
    private List<Animal> animalList;

    /**
     * First name of the owner
     */
    @Column(name = "o_firstname")
    private String firstName;

    /**
     * Middle name of the owner
     */
    @Column(name = "o_middlename")
    private String middleName;

    /**
     * Last name of the owner
     */
    @Column(name = "o_lastname")
    private String lastName;

    /**
     * Contact number of the owner
     */
    @Column(name = "o_contactnumber")
    private String contactNumber;

    /**
     * Email id of the owner
     */
    @Column(name = "o_emailid")
    private String emailId;

    /**
     * Address of the owner
     */
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

    /**
     * Returns the full name of the owner
     *
     * @return full name of the owner
     */
    public String getFullName() {
        StringBuilder sb = new StringBuilder();

        if (this.firstName != null) {
            sb.append(this.firstName);
        }

        if (this.middleName != null) {
            sb.append(" " + this.middleName);
        }

        if (this.lastName != null) {
            sb.append(" " + this.lastName);
        }

        // to handle null if no name is provided
        if (sb.length() == 0) {
            return "";
        }

        else {
            return sb.toString();
        }
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
