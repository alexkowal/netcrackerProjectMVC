package com.myproject.netcracker.domain;

public class Filter {
    Long brandId;
    Long modelId;
    Long charactId;
    Integer minMileage;



    Integer maxMileage;


    Integer minCost;
    Integer maxCost;
    String bodyType;
    String transmissionType;
    String driveUnite;


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

    public Long getCharactId() {
        return charactId;
    }

    public void setCharactId(Long charactId) {
        this.charactId = charactId;
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

    public Integer getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(Integer maxCost) {
        this.maxCost = maxCost;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getDriveUnite() {
        return driveUnite;
    }

    public void setDriveUnite(String driveUnite) {
        this.driveUnite = driveUnite;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }


    public Filter(Long brandId, Long modelId, Long charactId, Integer minMileage, Integer maxMileage, Integer minCost, Integer maxCost, String bodyType, String transmissionType, String driveUnite, Integer power) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.charactId = charactId;
        this.minMileage = minMileage;
        this.maxMileage = maxMileage;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.bodyType = bodyType;
        this.transmissionType = transmissionType;
        this.driveUnite = driveUnite;
        this.power = power;
    }

    public Integer getMinMileage() {
        return minMileage;
    }

    public void setMinMileage(Integer minMileage) {
        this.minMileage = minMileage;
    }

    public Integer getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(Integer maxMileage) {
        this.maxMileage = maxMileage;
    }

    public Filter() {

    }

   public void remove(){
        this.brandId = null;
        this.modelId = null;
        this.charactId = null;
        this.minCost = null;
        this.maxCost = null;
        this.bodyType = null;
        this.minMileage = null;
        this.maxMileage = null;
        this.transmissionType = null;
        this.driveUnite = null;
        this.power = null;
    }

    Integer power;
}