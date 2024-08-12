package com.app.entities;

import javax.persistence.*;
import java.util.Set;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "subscriptions")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "subscription")
    private Set<UserEntity> users;

}
