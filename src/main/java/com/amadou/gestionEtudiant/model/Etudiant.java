package com.amadou.gestionEtudiant.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "etudiant")
public class Etudiant extends AbstractEntity {

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthday")
    private Date birthday;

    @Embedded
    private Address address;

    @Column(name = "numbertel")
    private String numberTel;

    @Column(name = "photo")
    private String photo;

    @Column(name = "idschool")
    private Integer idSchool;

}
