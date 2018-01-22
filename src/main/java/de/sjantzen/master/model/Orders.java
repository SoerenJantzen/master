package de.sjantzen.master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "COMPANY_ID", insertable=false, updatable = false)
    private long companyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name = "USER_ID", insertable=false, updatable = false)
    private long userId;

    private String userEmail;

    public Orders() {
    }

    /**
     * Custom constructor.
     * @param userEmail
     * @param company
     * @param orderReceivedDatetime
     * @param dueDatetime
     * @param pickUpNumber
     * @param products
     */
    public Orders (String userEmail, Company company, Date orderReceivedDatetime, Date dueDatetime, String pickUpNumber, List<Product> products){
        this.userEmail = userEmail;
        this.company = company;
        this.orderReceivedDatetime = orderReceivedDatetime;
        this.dueDatetime = dueDatetime;
        this.pickUpNumber = pickUpNumber;
        this.products = products;
    }

    /**
     * Custom constructor.
     * @param userEmail
     * @param companyId
     * @param orderReceivedDatetime
     * @param dueDatetime
     * @param pickUpNumber
     * @param products
     */
    public Orders (String userEmail, long companyId, Date orderReceivedDatetime, Date dueDatetime, String pickUpNumber, List<Product> products){
        this.userEmail = userEmail;
        this.companyId = companyId;
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
    @JsonIgnore
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

    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        if (company != null) {
            return company.getId();
        }

        return companyId;
    }

    public String getCompanyName() {
        if (company != null) {
            return company.getName();
        }
        return "";
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "id: " + id + "; user: " + (user != null ? user.getId() : "") + "; company: "
                + (company != null ? company.getId() : "") + "; dueDatetime: " + dueDatetime
                + "; receivedDatetime: " + orderReceivedDatetime + "; pickUpNumber: " + pickUpNumber;
    }
}
