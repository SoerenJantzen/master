package de.sjantzen.master.model;

import de.sjantzen.master.menu.MenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Company {

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    @OneToMany(mappedBy = "company")
    private Set<Orders> orders;
    @OneToMany(mappedBy = "company")
    private Set<Category> categories;

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
     */
    public Company(String name, Address address, Set<Orders> orders, Set<Category> categories) {
        this.name = name;
        this.address = address;
        this.orders = orders;
        this.categories = categories;
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

    public Set<Product> getProducts() {
        if (!CollectionUtils.isEmpty(categories)) {
            return categories.stream().flatMap(category -> category.getProducts().stream()).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }


}
