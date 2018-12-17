package com.myproject.netcracker.domain;


import javax.persistence.*;

@Entity
@Table(name = "ad_model")
public class Model {
    @Id
    @Column(name = "id_model")
    private Long ModelId;

    @Column(name = "id_brand")
    private Long BrandId;

    @Column(name = "name")
    private String name;

    public Long getModelId() {
        return ModelId;
    }

    public void setModelId(Long modelId) {
        ModelId = modelId;
    }

    public Long getBrandId() {
        return BrandId;
    }

    public void setBrandId(Long brandId) {
        BrandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
