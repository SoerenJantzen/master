package de.sjantzen.master.model;

import javax.persistence.*;

/**
 * Created by sJantzen on 08.01.2018.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ROLE_ID")
    private long id;

    @Column(name="ROLE")
    private String role;

    public Role() {
    }

    public Role(String role){
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
