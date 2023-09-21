package com.amadou.gestionEtudiant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "school")
public class School extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "address")
    @Embedded
    private Address address;

    @Column(name = "numbertel")
    private String numberTel;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "school", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<User> users;

}
