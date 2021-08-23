package com.example.infostack;

public class Student {
    String name;
    String id;
    String register_no;
    String phone;
    String email;
    String college_name;
    String department;
    String cgpa,dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    String sslc_school;
    String hsc_school;
    String hsc_score;

    public Student(String name, String id, String register_no, String phone, String email, String college_name, String department, String cgpa, String sslc_school, String hsc_school, String hsc_score, String sslc_score, String blood_group, String college_year, String semester, String hsc_year, String sslc_year, String hsc_board, String sslc_board,String dob) {
        this.name = name;
        this.dob=dob;
        this.id = id;
        this.register_no = register_no;
        this.phone = phone;
        this.email = email;
        this.college_name = college_name;
        this.department = department;
        this.cgpa = cgpa;
        this.sslc_school = sslc_school;
        this.hsc_school = hsc_school;
        this.hsc_score = hsc_score;
        this.sslc_score = sslc_score;
        this.blood_group = blood_group;
        this.college_year = college_year;
        this.semester = semester;
        this.hsc_year = hsc_year;
        this.sslc_year = sslc_year;
        this.hsc_board = hsc_board;
        this.sslc_board = sslc_board;
    }

    String sslc_score;
    String blood_group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegister_no() {
        return register_no;
    }

    public void setRegister_no(String register_no) {
        this.register_no = register_no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getSslc_school() {
        return sslc_school;
    }

    public void setSslc_school(String sslc_school) {
        this.sslc_school = sslc_school;
    }

    public String getHsc_school() {
        return hsc_school;
    }

    public void setHsc_school(String hsc_school) {
        this.hsc_school = hsc_school;
    }

    public String getHsc_score() {
        return hsc_score;
    }

    public void setHsc_score(String hsc_score) {
        this.hsc_score = hsc_score;
    }

    public String getSslc_score() {
        return sslc_score;
    }

    public void setSslc_score(String sslc_score) {
        this.sslc_score = sslc_score;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getCollege_year() {
        return college_year;
    }

    public void setCollege_year(String college_year) {
        this.college_year = college_year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getHsc_year() {
        return hsc_year;
    }

    public void setHsc_year(String hsc_year) {
        this.hsc_year = hsc_year;
    }

    public String getSslc_year() {
        return sslc_year;
    }

    public void setSslc_year(String sslc_year) {
        this.sslc_year = sslc_year;
    }

    public String getHsc_board() {
        return hsc_board;
    }

    public void setHsc_board(String hsc_board) {
        this.hsc_board = hsc_board;
    }

    public String getSslc_board() {
        return sslc_board;
    }

    public void setSslc_board(String sslc_board) {
        this.sslc_board = sslc_board;
    }

    String college_year;
    String semester;
    String hsc_year;
    String sslc_year;
    String hsc_board;
    String sslc_board;
}
