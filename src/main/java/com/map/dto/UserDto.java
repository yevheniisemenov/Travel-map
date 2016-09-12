package com.map.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yevhenii Semenov
 */
public class UserDto {

    private long id;

    @Size(min = 2, max = 24)
    private String name;

    @Size(min = 2, max = 24)
    private String surname;

    @NotNull
    @Size(min = 2, max = 24)
    private String username;

    @Min(5)
    private String password;

    @NotNull
    @Email
    private String mail;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /*
     * Todo: Don't forget about equals and hashcode
     */
}
