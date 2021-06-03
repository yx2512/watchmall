package com.yx.watchmall.pojo;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "mall_product")
public class Product {
    private Integer Id;
    private Integer category;
    private String serialNum;
    private BigDecimal price;
    private String mainImg;
    private String subImg;
    private Double size;
    private Integer dialColor;
    private String movement;
    private String description;
    private Integer status;
    private Integer stock;
    private Date createTime;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Column(nullable = false)
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Column(nullable = false,length = 45)
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Column(nullable = false,precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column(nullable = false)
    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }
    @Column(nullable = false)
    public String getSubImg() {
        return subImg;
    }

    public void setSubImg(String subImg) {
        this.subImg = subImg;
    }
    @Column(nullable = false)
    public Integer getDialColor() {
        return dialColor;
    }

    public void setDialColor(Integer dialColor) {
        this.dialColor = dialColor;
    }
    @Column(nullable = false,length = 45)
    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }
    @Column(name = "product_desc",nullable=false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable=false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(nullable=false)
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    @Column(nullable = false)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", category=" + category +
                ", serialNum='" + serialNum + '\'' +
                ", price=" + price +
                ", mainImg='" + mainImg + '\'' +
                ", subImg='" + subImg + '\'' +
                ", size=" + size +
                ", dialColor=" + dialColor +
                ", movement='" + movement + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", stock=" + stock +
                '}';
    }


}
