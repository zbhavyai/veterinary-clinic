package ca.ucalgary.vetapp.model;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * User of the application
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Unique User id
     */
    @Id
    @SequenceGenerator(name = "sequence_users", sequenceName = "sequence_users", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_users")
    @Column(name = "u_userid")
    private long userId;

    /**
     * User joining date
     */
    @Column(name = "u_joiningdate", nullable = false)
    private LocalDate joiningDate;

    /**
     * User account activation date
     */
    @Column(name = "u_activationdate")
    private LocalDate activationDate;

    /**
     * User account termination date - eg for students it can be graduation date,
     * for teachers it can retirement date, for technicians it can be employment
     * cessation date
     */
    @Column(name = "u_terminationdate")
    private LocalDate terminationDate;

    /**
     * First name of the user
     */
    @Column(name = "u_firstname")
    private String firstName;

    /**
     * Middle name of the user
     */
    @Column(name = "u_middlename")
    private String middleName;

    /**
     * Last name of the user
     */
    @Column(name = "u_lastname")
    private String lastName;

    /**
     * Role of the user
     */
    @Column(name = "u_role")
    private UserRole role;

    /**
     * User login email
     */
    @Column(name = "u_emailid")
    private String emailId;

    /**
     * Hash of the user login password
     */
    @Column(name = "u_passwordhash")
    private String passwordHash;

    /**
     * Password salt
     */
    @Column(name = "u_passwordsalt")
    private String passwordSalt;

    /**
     * Status of the User - Active or Inactive
     */
    @Column(name = "u_status", nullable = false)
    private UserStatus status;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getJoiningDate() {
        return this.joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getActivationDate() {
        return this.activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDate getTerminationDate() {
        return this.terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
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
     * Returns the full name of the user
     *
     * @return full name of the user
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

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return this.passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
