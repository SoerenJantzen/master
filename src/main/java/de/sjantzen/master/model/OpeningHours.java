package de.sjantzen.master.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by sJantzen on 05.01.2018.
 */
@Entity
public class OpeningHours {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="MONDAY_START_TIME")
    private String mondayStartTime;

    @Column(name="MONDAY_END_TIME")
    private String mondayEndTime;

    @Column(name="THUESDAY_START_TIME")
    private String tuesdayStartTime;

    @Column(name="THUESDAY_END_TIME")
    private String tuesdayEndTime;

    @Column(name="WEDNESDAY_START_TIME")
    private String wednesdayStartTime;

    @Column(name="WEDNESDAY_END_TIME")
    private String wednesdayEndTime;

    @Column(name="THURSDAY_START_TIME")
    private String thursdayStartTime;

    @Column(name="THURSDAY_END_TIME")
    private String thursdayEndTime;

    @Column(name="FRIDAY_START_TIME")
    private String fridayStartTime;

    @Column(name="FRIDAY_END_TIME")
    private String fridayEndTime;

    @Column(name="SATURDAY_START_TIME")
    private String saturdayStartTime;

    @Column(name="SATURDAY_END_TIME")
    private String saturdayEndTime;

    @Column(name="SUNDAY_START_TIME")
    private String sundayStartTime;

    @Column(name="SUNDAY_END_TIME")
    private String sundayEndTime;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public OpeningHours() {

    }

    public OpeningHours(String mondayStartTime, String mondayEndTime, String tuesdayStartTime, String tuesdayEndTime,
                        String wednesdayStartTime, String wednesdayEndTime, String thursdayStartTime,
                        String thursdayEndTime, String fridayStartTime, String fridayEndTime, String saturdayStartTime,
                        String saturdayEndTime, String sundayStartTime, String sundayEndTime, Company company) {
        this.mondayStartTime = mondayStartTime;
        this.mondayEndTime = mondayEndTime;
        this.tuesdayStartTime = tuesdayStartTime;
        this.tuesdayEndTime = tuesdayEndTime;
        this.wednesdayStartTime = wednesdayStartTime;
        this.wednesdayEndTime = wednesdayEndTime;
        this.thursdayStartTime = thursdayStartTime;
        this.thursdayEndTime = thursdayEndTime;
        this.fridayStartTime = fridayStartTime;
        this.fridayEndTime = fridayEndTime;
        this.saturdayStartTime = saturdayStartTime;
        this.saturdayEndTime = saturdayEndTime;
        this.sundayStartTime = sundayStartTime;
        this.sundayEndTime = sundayEndTime;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMondayStartTime() {
        return mondayStartTime;
    }

    public void setMondayStartTime(String mondayStartTime) {
        this.mondayStartTime = mondayStartTime;
    }

    public String getMondayEndTime() {
        return mondayEndTime;
    }

    public void setMondayEndTime(String mondayEndTime) {
        this.mondayEndTime = mondayEndTime;
    }

    public String getTuesdayStartTime() {
        return tuesdayStartTime;
    }

    public void setTuesdayStartTime(String tuesdayStartTime) {
        this.tuesdayStartTime = tuesdayStartTime;
    }

    public String getTuesdayEndTime() {
        return tuesdayEndTime;
    }

    public void setTuesdayEndTime(String tuesdayEndTime) {
        this.tuesdayEndTime = tuesdayEndTime;
    }

    public String getWednesdayStartTime() {
        return wednesdayStartTime;
    }

    public void setWednesdayStartTime(String wednesdayStartTime) {
        this.wednesdayStartTime = wednesdayStartTime;
    }

    public String getWednesdayEndTime() {
        return wednesdayEndTime;
    }

    public void setWednesdayEndTime(String wednesdayEndTime) {
        this.wednesdayEndTime = wednesdayEndTime;
    }

    public String getThursdayStartTime() {
        return thursdayStartTime;
    }

    public void setThursdayStartTime(String thursdayStartTime) {
        this.thursdayStartTime = thursdayStartTime;
    }

    public String getThursdayEndTime() {
        return thursdayEndTime;
    }

    public void setThursdayEndTime(String thursdayEndTime) {
        this.thursdayEndTime = thursdayEndTime;
    }

    public String getFridayStartTime() {
        return fridayStartTime;
    }

    public void setFridayStartTime(String fridayStartTime) {
        this.fridayStartTime = fridayStartTime;
    }

    public String getFridayEndTime() {
        return fridayEndTime;
    }

    public void setFridayEndTime(String fridayEndTime) {
        this.fridayEndTime = fridayEndTime;
    }

    public String getSaturdayStartTime() {
        return saturdayStartTime;
    }

    public void setSaturdayStartTime(String saturdayStartTime) {
        this.saturdayStartTime = saturdayStartTime;
    }

    public String getSaturdayEndTime() {
        return saturdayEndTime;
    }

    public void setSaturdayEndTime(String saturdayEndTime) {
        this.saturdayEndTime = saturdayEndTime;
    }

    public String getSundayStartTime() {
        return sundayStartTime;
    }

    public void setSundayStartTime(String sundayStartTime) {
        this.sundayStartTime = sundayStartTime;
    }

    public String getSundayEndTime() {
        return sundayEndTime;
    }

    public void setSundayEndTime(String sundayEndTime) {
        this.sundayEndTime = sundayEndTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
