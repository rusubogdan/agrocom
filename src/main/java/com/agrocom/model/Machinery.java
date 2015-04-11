package com.agrocom.model;

import javax.persistence.*;

@Entity
@Table(name = "machinery")
public class Machinery {

    @Id
    @GeneratedValue(generator = "machinery_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "machinery_id_seq", sequenceName = "machinery_id_seq", allocationSize = 1)
    private Integer machineryId;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @Column(name = "machinery_type")
    private String machineryType;

    @Column(name = "name")
    private String machineryName;

    @Column(name = "production_year")
    private Integer productionYear;

    // litres of diesel fuel (oil) per hundred kilometers
    @Column(name = "consumption")
    private Integer consumption;

    @Column(name = "km_on_board")
    private Long kmOnBoard;
}
