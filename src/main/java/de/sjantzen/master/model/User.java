package de.sjantzen.master.model;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.math.BigDecimal;
import java.util.Set;

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

    @Column(name="EMAIL")
    private String eMail;

    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;

    /*
     * TODO Zahlungsmehtoden - hier weiß ich noch nicht, wie ich das genau umsetzen will
     * - entweder mit spalten wie 'Kreditkarte', 'PaypalAdresse' und so
     * - ODER mit einzelnen Tabellen für die Zahlungsmehtoden
     */

    public User() {
    }

    /**
     * Constructor.
     * @param userName
     * @param password
     * @param lockCode
     * @param eMail
     * @param orders
     */
    public User(String userName, String password, String lockCode, String eMail, Set<Orders> orders) {
        this.userName = userName;
        this.password = password;
        this.lockCode = lockCode;
        this.eMail = eMail;
        this.orders = orders;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
