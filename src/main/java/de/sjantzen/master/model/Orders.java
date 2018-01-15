package de.sjantzen.master.model;

import de.sjantzen.master.utils.DateFormat;
import de.sjantzen.master.utils.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Orders {

    private static final Logger LOG = LoggerFactory.getLogger(Orders.class);

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

    @Column(name="PICK_UP_NUMBER")
    private String pickUpNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPANY_ID")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public Orders() {
    }

    public Orders (Date orderReceivedDatetime, Date dueDatetime, String pickUpNumber, List<Product> products){
        this.orderReceivedDatetime = orderReceivedDatetime;
        this.dueDatetime = dueDatetime;
        this.pickUpNumber = pickUpNumber;
        this.products = products;
    }

    /**
     * Constructor.
     * @param user
     * @param company
     * @param orderReceivedDatetime
     * @param dueDatetime
     * @param products
     * @param pickUpNumber
     */
    public Orders(User user, Company company, Date orderReceivedDatetime, Date dueDatetime, List<Product> products, String pickUpNumber) {
        this.user = user;
        this.company = company;
        this.orderReceivedDatetime = orderReceivedDatetime;
        this.dueDatetime = dueDatetime;
        this.products = products;
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

    public String getDueDatetimeTranslated() {
        return Translator.getDatetimeFormatted(dueDatetime, DateFormat.MIDDLE);
    }

    public void setDueDatetime(Date dueDatetime) {
        this.dueDatetime = dueDatetime;
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns a map containing the products'names and the amount of this products.
     * @return
     */
    public Map<String, Integer> getProductsMap() {
        Map<String, Integer> rv = new HashMap<>();

        for (Product product : products) {
            rv.put(product.getName(), (rv.containsKey(product.getName()) ? rv.get(product.getName()) + 1 : 1));
        }

        return rv;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public String getOrderReceivedDatetimeTranslated() {
        return Translator.getDatetimeFormatted(orderReceivedDatetime, DateFormat.MIDDLE);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
