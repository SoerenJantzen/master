package de.sjantzen.master.model;

import de.sjantzen.master.constants.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;
    @Column(name="ORDER_RECEIVED_DATETIME")
    private Date orderReceivedDatetime;
    @Column(name="DUE_DATETIME")
    private Date dueDatetime;
    @ManyToMany
    @JoinTable(name = "ORDER2PRODUCT")
    private List<Product> products; // has to be a list, products can be ordered multiple times
    @Column(name="ALREADY_PAYED")
    private Boolean alreadyPayed;
    @Column(name="PICK_UP_NUMBER")
    private String pickUpNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPANY_ID")
    private Company company;

    public Orders() {
    }

    /**
     * Constructor.
     * @param companyId
     * @param userId
     * @param orderReceivedDatetime
     * @param dueDatetime
     * @param products
     * @param alreadyPayed
     * @param pickUpNumber
     * @param orderStatus
     */
    public Orders(BigDecimal companyId, BigDecimal userId, Date orderReceivedDatetime, Date dueDatetime, List<Product> products, Boolean alreadyPayed, String pickUpNumber, OrderStatus orderStatus) {
        this.orderReceivedDatetime = orderReceivedDatetime;
        this.dueDatetime = dueDatetime;
        this.products = products;
        this.alreadyPayed = alreadyPayed;
        this.pickUpNumber = pickUpNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDueDatetime() {
        return dueDatetime;
    }

    public void setDueDatetime(Date dueDatetime) {
        this.dueDatetime = dueDatetime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Boolean getAlreadyPayed() {
        return alreadyPayed;
    }

    public void setAlreadyPayed(Boolean alreadyPayed) {
        this.alreadyPayed = alreadyPayed;
    }

    public String getPickUpNumber() {
        return pickUpNumber;
    }

    public void setPickUpNumber(String pickUpNumber) {
        this.pickUpNumber = pickUpNumber;
    }

    public Date getOrderReceivedDatetime() {
        return orderReceivedDatetime;
    }

    public void setOrderReceivedDatetime(Date orderReceivedDatetime) {
        this.orderReceivedDatetime = orderReceivedDatetime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


}
