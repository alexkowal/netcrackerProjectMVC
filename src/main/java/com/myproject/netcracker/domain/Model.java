package com.myproject.netcracker.domain;


import javax.persistence.*;

@Entity
@Table(name = "ad_model")
public class Model {
    @Id
    @Column(name = "id_model")
    private Long modelId;

    @Column(name = "id_brand")
    private Long brandId;

    @Column(name = "name")
    private String name;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
