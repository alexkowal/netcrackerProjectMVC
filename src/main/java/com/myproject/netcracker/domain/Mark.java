package com.myproject.netcracker.domain;

import javax.persistence.*;


@Entity
@Table(name = "ad_mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mark")
    private Long markId;

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "id_adv")
    private Long advId;

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

}