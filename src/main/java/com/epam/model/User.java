package com.epam.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Sahak_Babayan on 3/29/2016.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "{err.field.full.name.required}")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "{err.field.country.required}")
    @Column(name = "country")
    private String country;

    @NotEmpty(message = "{err.field.birthday.required}")
    @Column(name = "birthday")
    private String birthday;

    @Email(message = "{err.field.email.invalid}")
    @NotEmpty(message = "{err.field.email.required}")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "{err.field.password.required}")
    @Column(name = "password")
    private String password;

    public User(){}

    public User(User user) {
        this.fullName = user.getFullName();
        this.country = user.getCountry();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
