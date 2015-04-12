package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "society")
public class Society implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator = "society_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "society_id_seq", sequenceName = "society_id_seq", allocationSize = 1)
    @Column(name = "society_id")
    private Long societyId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "employingSociety")
    private List<User> employees;

    @OneToMany(mappedBy = "society")
    private List<Infield> infields;

    @OneToMany(mappedBy = "society")
    private List<Garage> garages;


    @Column(name = "society_name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

}