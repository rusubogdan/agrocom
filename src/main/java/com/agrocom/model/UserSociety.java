package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_society")
public class UserSociety implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_society_id_seq")
    @SequenceGenerator(name = "user_society_id_seq",
            sequenceName = "user_society_id_seq", allocationSize = 1)
    @Column(name = "user_society_id")
    private Long userSocietyId;

    public UserSociety(User user, Society society, Role role) {
        this.user = user;
        this.society = society;
        this.role = role;
    }

    public UserSociety() {
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserSocietyId() {
        return userSocietyId;
    }

    public void setUserSocietyId(Long userSocietyId) {
        this.userSocietyId = userSocietyId;
    }
}
