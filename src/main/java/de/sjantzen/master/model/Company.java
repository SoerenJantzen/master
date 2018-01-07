package de.sjantzen.master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Company {

    private static final Logger LOG = LoggerFactory.getLogger(Company.class);

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @JsonManagedReference
    @OneToOne(mappedBy = "company")
    private Address address;

    @OneToMany(mappedBy = "company")
    private Set<Orders> orders;

    @OneToMany(mappedBy = "company")
    private Set<Category> categories;

    @JsonManagedReference
    @OneToOne(mappedBy = "company")
    private OpeningHours openingHours;

    /**
     * Default constructor.
     */
    public Company() {

    }

    /**
     * Full constructor.
     * @param name
     * @param address
     * @param orders
     * @param categories
     * @param openingHours
     */
    public Company(String name, Address address, Set<Orders> orders, Set<Category> categories, OpeningHours openingHours) {
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.categories = categories;
        this.openingHours = openingHours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonIgnore
    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Category> getCategories() {
        if (!CollectionUtils.isEmpty(categories)) {
            return categories;
        }
        return new HashSet<>();
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @JsonIgnore
    public Set<Product> getProducts() {
        if (!CollectionUtils.isEmpty(categories)) {
            return categories.stream().flatMap(category -> category.getProducts().stream()).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }
}
