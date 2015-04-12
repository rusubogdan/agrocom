package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "work_history")
public class WorkHistory implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator = "society_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "society_id_seq", sequenceName = "society_id_seq", allocationSize = 1)
    @Column(name = "work_his_id")
    private Long workHistoryId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User worker;

    @ManyToOne
    @JoinColumn(name = "infield_id")
    private Infield infield;

    @ManyToOne
    @JoinColumn(name = "machinery_id")
    private Machinery machinery;

    @Column(name = "work_type")
    private WorkType workType;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

}
