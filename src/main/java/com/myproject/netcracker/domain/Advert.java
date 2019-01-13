package com.myproject.netcracker.domain;



import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate addDate;

    @Column(name = "id_brand")
    private Long brandId;

    @Column(name = "id_model")
    private Long modelId;

    @Column(name = "id_charact")
    private Long charactId;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "cost_val")
    private Integer costVal;



    @Column(name = "fact_year")
    private Integer factYear;

    @Column(name = "isactive")
    private Boolean isactive;

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

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
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

    public Long getCharactId() {
        return charactId;
    }

    public void setCharactId(Long charactId) {
        this.charactId = charactId;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getCostVal() {
        return costVal;
    }

    public void setCostVal(Integer costVal) {
        this.costVal = costVal;
    }

    public Integer getFactYear() {
        return factYear;
    }

    public void setFactYear(Integer factYear) {
        this.factYear = factYear;
    }



    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (!id.equals(advert.id)) return false;
        if (!ownerId.equals(advert.ownerId)) return false;
        if (!title.equals(advert.title)) return false;
        if (!description.equals(advert.description)) return false;
        if (!addDate.equals(advert.addDate)) return false;
        if (!brandId.equals(advert.brandId)) return false;
        if (!modelId.equals(advert.modelId)) return false;
        if (!charactId.equals(advert.charactId)) return false;
        if (!mileage.equals(advert.mileage)) return false;
        if (!costVal.equals(advert.costVal)) return false;
        if(!isactive.equals(advert.isactive))return false;
        return factYear.equals(advert.factYear);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ownerId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + addDate.hashCode();
        result = 31 * result + brandId.hashCode();
        result = 31 * result + modelId.hashCode();
        result = 31 * result + charactId.hashCode();
        result = 31 * result + mileage.hashCode();
        result = 31 * result + isactive.hashCode();
        result = 31 * result + costVal.hashCode();
        result = 31 * result + factYear.hashCode();
        return result;
    }
}
