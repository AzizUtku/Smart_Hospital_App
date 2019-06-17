package org.azutka.akllhastanesistemi.models;

import com.google.gson.annotations.SerializedName;

public class Personnel {


    @SerializedName("id")
    public String id;

    @SerializedName("ad")
    public String name;

    @SerializedName("soyad")
    public String surname;

    @SerializedName("tc_no")
    public String tc;

    @SerializedName("telefon_no")
    public String phone;

    @SerializedName("yas")
    public String age;

    @SerializedName("bolum")
    public String department;

    @SerializedName("cinsiyet")
    public String gender;

    @SerializedName("yetkili")
    public String status;

    @SerializedName("hash")
    public String hash;


    public Personnel() {
    }

    public Personnel(String id, String name, String surname, String tc, String phone, String age, String department, String gender, String status, String hash) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.phone = phone;
        this.age = age;
        this.department = department;
        this.gender = gender;
        this.status = status;
        this.hash = hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tc='" + tc + '\'' +
                ", phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
