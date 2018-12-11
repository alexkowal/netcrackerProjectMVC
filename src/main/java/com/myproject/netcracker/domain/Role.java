package com.myproject.netcracker.domain;

import javax.persistence.*;

@Entity
@Table(name = "ad_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_role;

    public Long getIdRole() {
        return id_role;
    }

    public void setIdRole(Long id_role) {
        this.id_role = id_role;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    private String descr;
}
