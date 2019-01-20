package com.myproject.netcracker.domain;

import javax.persistence.*;

@Entity
@Table(name = "ad_pict")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pict")
    private Long pictId;



    @Column(name = "id_adv")
    private Long advertId;


    @Column(name = "pict_path")
    private String path;

    public Long getPictId() {
        return pictId;
    }

    public void setPictId(Long pictId) {
        this.pictId = pictId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
    }


    public  void changeName(){
        String name = this.getPath();
        String temp = "" + Math.random()*100000000;
        temp+=name;
        this.setPath(name);

    }

}
