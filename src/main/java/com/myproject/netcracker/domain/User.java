package com.myproject.netcracker.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "ad_users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    @NotNull
    @Size(min = 8, max = 32)
    private String login;

    private String password;

    @NotNull
    private String confirmpassword;

    private String userName;

    @Email
    private String email;

    private Long id_role;

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    private Boolean isactive;


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id_user) {
        this.idUser = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdRole() {
        return id_role;
    }

    public void setIdRole(Long id_role) {
        this.id_role = id_role;
    }


}
