package com.agrocom.model;

import javax.persistence.*;

@Entity
@Table(name = "infield")
public class Infield {

    @Id
    @GeneratedValue(generator = "infield_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "infield_id_seq", sequenceName = "infield_id_seq", allocationSize = 1)
    @Column(name = "infield_id")
    private Integer infieldId;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @Column(name = "code")
    private String locationCode;

    @Column(name = "county")
    private String county;

    @Column(name = "village")
    private String village;

    @Column(name = "five_years_ago")
    private String fiveYearsAgo;

    @Column(name = "four_years_ago")
    private String fourYearsAgo;

    @Column(name = "three_years_ago")
    private String threeYearsAgo;

    @Column(name = "two_years_ago")
    private String twoYearsAgo;

    @Column(name = "last_year")
    private String lastYear;
}
