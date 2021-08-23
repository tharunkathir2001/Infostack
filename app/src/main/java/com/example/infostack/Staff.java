package com.example.infostack;

public class Staff {
    String name;

    public Staff(String name, String mail, String collegeName, String dept) {
        this.name = name;
        this.mail = mail;
        this.collegeName = collegeName;
        this.dept = dept;
    }

    String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    String collegeName;
    String dept;
}
