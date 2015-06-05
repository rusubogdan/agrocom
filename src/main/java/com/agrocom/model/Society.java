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

    public Long getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Long societyId) {
        this.societyId = societyId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public List<Infield> getInfields() {
        return infields;
    }

    public void setInfields(List<Infield> infields) {
        this.infields = infields;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Society society = (Society) o;

        if (address != null ? !address.equals(society.address) : society.address != null) return false;
        if (employees != null ? !employees.equals(society.employees) : society.employees != null) return false;
        if (garages != null ? !garages.equals(society.garages) : society.garages != null) return false;
        if (infields != null ? !infields.equals(society.infields) : society.infields != null) return false;
        if (name != null ? !name.equals(society.name) : society.name != null) return false;
        if (owner != null ? !owner.equals(society.owner) : society.owner != null) return false;
        if (phone != null ? !phone.equals(society.phone) : society.phone != null) return false;
        if (societyId != null ? !societyId.equals(society.societyId) : society.societyId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = societyId != null ? societyId.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        result = 31 * result + (infields != null ? infields.hashCode() : 0);
        result = 31 * result + (garages != null ? garages.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Society{" +
                "societyId=" + societyId +
                ", owner=" + owner +
                ", employees=" + employees +
                ", infields=" + infields +
                ", garages=" + garages +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Society() {
    }

    public Society(User owner, List<User> employees, List<Infield> infields, List<Garage> garages, String name,
                   String phone, String address) {
        this.owner = owner;
        this.employees = employees;
        this.infields = infields;
        this.garages = garages;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
