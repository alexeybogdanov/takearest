package com.example.takearest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;

}
