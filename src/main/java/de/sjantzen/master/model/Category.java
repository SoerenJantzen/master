package de.sjantzen.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name = "ORDER_NUMBER")
    private int orderNumber;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCIPTION")
    private String description;

    @Column(name="IS_MAIN_CATEGORY")
    private boolean isMainCategory;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPANY_ID")
    private Company company;

    public Category() {
    }

    /**
     * Constructor.
     * @param orderNumber
     * @param name
     * @param description
     * @param isMainCategory
     * @param products
     * @param company
     */
    public Category(int orderNumber, String name, String description, boolean isMainCategory, Set<Product> products, Company company) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.description = description;
        this.isMainCategory = isMainCategory;
        this.products = products;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMainCategory() {
        return isMainCategory;
    }

    public void setMainCategory(boolean mainCategory) {
        isMainCategory = mainCategory;
    }

    public Set<Product> getProducts() {
        if (!CollectionUtils.isEmpty(products)) {
            return products;
        }
        return new HashSet<>();
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
