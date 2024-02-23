package com.develhope.spring.user;

import java.util.List;

import com.develhope.spring.configurations.Role;
import com.develhope.spring.order.OrderInfo;
import com.develhope.spring.rent.RentInfo;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<OrderInfo> order;

    @OneToMany(mappedBy = "user")
    private List<RentInfo> rent;

    @Enumerated(EnumType.STRING)
    private Role role;

}
