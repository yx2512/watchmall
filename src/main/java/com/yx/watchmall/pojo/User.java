package com.yx.watchmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mall_user")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,unique = true,length = 45)
    private String username;
    @Column(nullable = false,unique = true,length = 45)
    private String email;
    @Column(nullable = false,length = 45)
    private String password;
    @Column(length = 20)
    private String phone;
    @Column(nullable = false)
    private Integer role;
    private Date create_time;
    private Date update_time;

    public User() {
    }

    public User(Integer id, String username, String email, String password, String phone, Integer role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
}
