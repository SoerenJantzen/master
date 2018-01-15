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

    @Column(name="USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="LOCK_CODE")
    private String lockCode;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "ACTIVE")
    private int active;

    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER2ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    /*
     * TODO Zahlungsmehtoden - hier weiß ich noch nicht, wie ich das genau umsetzen will
     * - entweder mit spalten wie 'Kreditkarte', 'PaypalAdresse' und so
     * - ODER mit einzelnen Tabellen für die Zahlungsmehtoden
     */

    public User() {
    }

    public User(String username, String password, String lockCode, String email, int active) {
        this.username = username;
        this.password = password;
        this.lockCode = lockCode;
        this.email = email;
        this.active = active;
    }

    /**
     * Constructor.
     * @param username
     * @param password
     * @param lockCode
     * @param email
     * @param orders
     */
    public User(String username, String password, String lockCode, String email, int active, Set<Orders> orders) {
        this.username = username;
        this.password = password;
        this.lockCode = lockCode;
        this.email = email;
        this.active = active;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User; id: " + id + "; username: " + username + "; email: " + email + "; active: " + active;
    }
}
