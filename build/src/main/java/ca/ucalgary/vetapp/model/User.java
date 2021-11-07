package ca.ucalgary.vetapp.model;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(name = "sequence_user", sequenceName = "sequence_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_user")
    @Column(name = "u_userid")
    private long userId;

    @Column(name = "u_firstname")
    private String firstName;

    @Column(name = "u_middlename")
    private String middleName;

    @Column(name = "u_lastname")
    private String lastName;

    @Column(name = "u_role")
    private UserRole role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
