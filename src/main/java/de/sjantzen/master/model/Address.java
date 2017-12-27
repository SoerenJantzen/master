package de.sjantzen.master.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="STREET")
    private String street;
    @Column(name="ZIP")
    private String zip;
    @Column(name="CITY")
    private String city;
    @OneToOne(mappedBy = "address")
    private Company company;

    public Address() {
    }

    /**
     * Constructor.
     * @param street
     * @param zip
     * @param city
     */
    public Address(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}