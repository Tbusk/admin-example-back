package com.seng315.finalproject.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "user_info_id")
    private Integer userInfoID;

    @Column(name = "address_id")
    private Integer addressID;

    @Column(name = "created_at")
    private Date createdAt;

    private String username;
    private String password;
    private String role;
    private Boolean active;
}
