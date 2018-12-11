package com.myproject.netcracker.domain;



import javax.persistence.*;

@Entity
@Table(name = "ad_advert")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adv")
    private Long id;
    @Column(name = "id_owner")
    private Long ownerId;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "add_date")
    private String addDate;

    @Column(name = "id_brand")
    private String brandId;

    @Column(name = "id_model")
    private String modelId;

    @Column(name = "id_charact")
    private String charactId;

    @Column(name = "mileage")
    private String mileage;

    @Column(name = "cost_val")
    private String costVal;


    @Column(name = "fact_year")
    private String factYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCharactId() {
        return charactId;
    }

    public void setCharactId(String charactId) {
        this.charactId = charactId;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getCostVal() {
        return costVal;
    }

    public void setCostVal(String costVal) {
        this.costVal = costVal;
    }

    public String getFactYear() {
        return factYear;
    }

    public void setFactYear(String factYear) {
        this.factYear = factYear;
    }
}
