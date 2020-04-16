package com.vpetrou.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private long contactId;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 4, max = 30)
    private String name;

    @Pattern(regexp = "^[a-zA-Z]{4}[0-9]{4}$")
    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "category")
    private String category;

    @Pattern(regexp = "^[0-9]{14}$")
    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "^(.+)@(.+)$")
    @Column(name = "email")
    private String email;

    @Size(min = 0, max = 30)
    @Column(name = "city")
    private String city;

    @Size(min = 0, max = 30)
    @Column(name = "country")
    private String country;

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}