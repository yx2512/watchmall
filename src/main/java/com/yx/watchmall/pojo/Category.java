package com.yx.watchmall.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mall_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer Id;
    @Column(nullable = false)
    Integer parentId;
    @Column(nullable = false,length = 64,unique = true)
    String category;
    @Column(nullable = false)
    Integer sortOrder;
    @Column(nullable = false)
    Integer status;
    Date createTime;
    Date updateTime;
}
