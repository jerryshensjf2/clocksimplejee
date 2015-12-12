package com.javaforever.clocksimplejee4.domain;

public class User{
    private long id;
    private long empid;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String sex;
    private String isadmin;
    private String isactive;
    private String pin;
    private int loginfailure;
    private String namec;
    private String namej;
    private String address;
    private String address1;
    private String phone;
    private String mobile;
    private String confirmpassword;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmpid() {
        return this.empid;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIsadmin() {
        return this.isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getIsactive() {
        return this.isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getLoginfailure() {
        return this.loginfailure;
    }

    public void setLoginfailure(int loginfailure) {
        this.loginfailure = loginfailure;
    }

    public String getNamec() {
        return this.namec;
    }

    public void setNamec(String namec) {
        this.namec = namec;
    }

    public String getNamej() {
        return this.namej;
    }

    public void setNamej(String namej) {
        this.namej = namej;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getConfirmpassword() {
        return this.confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getFullName(){
    	return (firstname + " " + lastname);
    }
}
