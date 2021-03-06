package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

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
    private Set<Society> ownedSocieties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "society_id")
    private Society employingSociety;

    @OneToMany(mappedBy = "tenant")
    private Set<Infield> infields;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pin")
    private String PIN;

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

    @Column(name = "token")
    private String token;

    public User(User other) {
        this.userId = other.userId;
        this.role = other.role;
        this.ownedSocieties = other.ownedSocieties;
        this.employingSociety = other.employingSociety;
        this.infields = other.infields;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.PIN = other.PIN;
        this.phone = other.phone;
        this.mobile = other.mobile;
        this.email = other.email;
        this.password = other.password;
        this.registerDate = other.registerDate;
        this.isConfirmed = other.isConfirmed;
        this.deletedDate = other.deletedDate;
        this.token = other.token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Society> getOwnedSocieties() {
        return ownedSocieties;
    }

    public void setOwnedSocieties(Set<Society> ownedSocieties) {
        this.ownedSocieties = ownedSocieties;
    }

    public Society getEmployingSociety() {
        return employingSociety;
    }

    public void setEmployingSociety(Society employingSociety) {
        this.employingSociety = employingSociety;
    }

    public Set<Infield> getInfields() {
        return infields;
    }

    public void setInfields(Set<Infield> infields) {
        this.infields = infields;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String pin) {
        this.PIN = pin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (deletedDate != null ? !deletedDate.equals(user.deletedDate) : user.deletedDate != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
//        if (employingSociety != null ? !employingSociety.equals(user.employingSociety) : user.employingSociety != null)
//            return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (PIN != null ? !PIN.equals(user.PIN) : user.PIN != null) return false;
//        if (infields != null ? !infields.equals(user.infields) : user.infields != null) return false;
        if (isConfirmed != null ? !isConfirmed.equals(user.isConfirmed) : user.isConfirmed != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (mobile != null ? !mobile.equals(user.mobile) : user.mobile != null) return false;
//        if (ownedSocieties != null ? !ownedSocieties.equals(user.ownedSocieties) : user.ownedSocieties != null)
//            return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (registerDate != null ? !registerDate.equals(user.registerDate) : user.registerDate != null) return false;
//        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (token != null ? !token.equals(user.token) : user.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
//        result = 31 * result + (ownedSocieties != null ? ownedSocieties.hashCode() : 0);
        result = 31 * result + (employingSociety != null ? employingSociety.hashCode() : 0);
        result = 31 * result + (infields != null ? infields.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (PIN != null ? PIN.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (isConfirmed != null ? isConfirmed.hashCode() : 0);
        result = 31 * result + (deletedDate != null ? deletedDate.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role=" + role +
                ", ownedSocieties=" + ownedSocieties +
                ", employingSociety=" + employingSociety +
                ", infields=" + infields +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", PIN='" + PIN +
                ", phone='" + phone +
                ", mobile='" + mobile +
                ", email='" + email +
                ", password='" + password +
                ", registerDate=" + registerDate +
                ", isConfirmed=" + isConfirmed +
                ", deletedDate=" + deletedDate +
                ", token=" + token +
                '}';
    }

    public User() {
    }

    public User(Role role, Set<Society> ownedSocieties, Society employingSociety, Set<Infield> infields,
                String firstName, String pin, String lastName, String phone, String mobile, String email, String password,
                Timestamp registerDate, Boolean isConfirmed, Timestamp deletedDate, String token) {
        this.role = role;
        this.ownedSocieties = ownedSocieties;
        this.employingSociety = employingSociety;
        this.infields = infields;
        this.firstName = firstName;
        this.PIN = pin;
        this.lastName = lastName;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.isConfirmed = isConfirmed;
        this.deletedDate = deletedDate;
        this.token = token;
    }

}
