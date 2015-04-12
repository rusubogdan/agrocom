package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "garage")
public class Garage implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator = "garage_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "garage_id_seq", sequenceName = "garage_id_seq", allocationSize = 1)
    private Long garageId;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @OneToMany(mappedBy = "garage")
    private List<Machinery> machines;

    // measured in tones
    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public List<Machinery> getmachines() {
        return machines;
    }

    public void setmachines(List<Machinery> machines) {
        this.machines = machines;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Garage garage = (Garage) o;

        if (address != null ? !address.equals(garage.address) : garage.address != null) return false;
        if (capacity != null ? !capacity.equals(garage.capacity) : garage.capacity != null) return false;
        if (garageId != null ? !garageId.equals(garage.garageId) : garage.garageId != null) return false;
        if (machines != null ? !machines.equals(garage.machines) : garage.machines != null) return false;
        if (name != null ? !name.equals(garage.name) : garage.name != null) return false;
        if (society != null ? !society.equals(garage.society) : garage.society != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = garageId != null ? garageId.hashCode() : 0;
        result = 31 * result + (society != null ? society.hashCode() : 0);
        result = 31 * result + (machines != null ? machines.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "garageId=" + garageId +
                ", society=" + society +
                ", machines=" + machines +
                ", capacity=" + capacity +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
