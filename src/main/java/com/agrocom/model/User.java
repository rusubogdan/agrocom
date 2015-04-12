package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

    // yep, it's 42 :D
    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    // unidirectional relationship between user and role !
    // role is independent of user
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<Society> ownedSocieties;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society employingSociety;

    @OneToMany(mappedBy = "tenant")
    private List<Infield> infields;





    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "register_date")
    private Timestamp registerDate;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    // todo setter getter constructors to string hash
}
