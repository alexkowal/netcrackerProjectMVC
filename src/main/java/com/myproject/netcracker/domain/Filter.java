package com.myproject.netcracker.domain;

public class Filter {
    Long brandId;
    Long modelId;
    Long charactId;
    Long minMileage;



    Long maxMileage;


    Long minCost;
    Long maxCost;
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

    public Long getMinCost() {
        return minCost;
    }

    public void setMinCost(Long minCost) {
        this.minCost = minCost;
    }

    public Long getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(Long maxCost) {
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


    public Filter(Long brandId, Long modelId, Long charactId, Long minMileage, Long maxMileage, Long minCost, Long maxCost, String bodyType, String transmissionType, String driveUnite, Long minPower, Long maxPower) {
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
        this.minPower = minPower;
        this.maxPower = maxPower;
    }

    public Long getMinMileage() {
        return minMileage;
    }

    public void setMinMileage(Long minMileage) {
        this.minMileage = minMileage;
    }

    public Long getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(Long maxMileage) {
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
        this.minPower = null;
        this.maxPower = null;
    }

    Long minPower;

    public Long getMinPower() {
        return minPower;
    }

    public void setMinPower(Long minPower) {
        this.minPower = minPower;
    }

    public Long getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Long maxPower) {
        this.maxPower = maxPower;
    }

    Long maxPower;
}
