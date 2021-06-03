package com.yx.watchmall.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mall_feature")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Feature {
    private Integer Id;
    private String feature;
    private Date createTime;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Column(nullable = false, unique = true)
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature1 = (Feature) o;
        return Id.equals(feature1.Id) && feature.equals(feature1.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, feature);
    }

    @Override
    public String toString() {
        return "Feature{" +
                "Id=" + Id +
                ", feature='" + feature + '\'' +
                '}';
    }
}
