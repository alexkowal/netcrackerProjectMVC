package com.myproject.netcracker.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "ad_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
    private String login;
    private String password;
    private String userName;
    private String email;
    private Long id_role;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
