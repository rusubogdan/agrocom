package com.agrocom.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(generator = "garage_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "garage_id_seq", sequenceName = "garage_id_seq", allocationSize = 1)
    private Integer garageId;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @OneToMany(mappedBy = "garage")
    private List<Machinery> machineries;

    // measured in tones
    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

}
