package com.myproject.netcracker.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad_charact")
public class Charact {

    @Id
    @Column(name = "id_charact")
    private Long charactId;

    @Column(name = "id_brand")
    private Long brandId;

    @Column(name = "id_model")
    private Long modelId;

    @Column(name = "body")
    private String body;


    @Column(name = "transmission")
    private String transmission;

    @Column(name = "drive_unit")
    private String driveUnit;

    public Long getCharactId() {
        return charactId;
    }

    public void setCharactId(Long charactId) {
        this.charactId = charactId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    @Column(name = "power")
    private Long power;
}
