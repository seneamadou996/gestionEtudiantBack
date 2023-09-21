package com.amadou.gestionEtudiant.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "numbertel")
    private String numberTel;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @Embedded
    private Address addess;

    @ManyToOne()
    @JoinColumn(name = "idschool")
    private School school;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_users")
    private List<Role> roles = new ArrayList<>();

}
