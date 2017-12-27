package de.sjantzen.master.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;
    @Column(name="USERNAME")
    private String userName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="LOCK_CODE")
    private String lockCode;
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="EMAIL")
    private String eMail;

    public User() {
    }

    /**
     * Constructor.
     * @param userName
     * @param password
     * @param lockCode
     * @param firstName
     * @param lastName
     * @param eMail
     */
    public User(String userName, String password, String lockCode, String firstName, String lastName, String eMail) {
        this.userName = userName;
        this.password = password;
        this.lockCode = lockCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        //this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /*
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    */


}
