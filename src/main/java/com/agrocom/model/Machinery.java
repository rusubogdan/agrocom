package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "machinery")
public class Machinery implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator = "machinery_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "machinery_id_seq", sequenceName = "machinery_id_seq", allocationSize = 1)
    @Column(name = "machinery_id")
    private Long machineryId;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @Column(name = "machinery_type")
    private MachineryType machineryType;

    @Column(name = "name")
    private String machineryName;

    @Column(name = "production_year")
    private Integer productionYear;

    // litres of diesel fuel (oil) per hundred kilometers
    @Column(name = "consumption")
    private Integer consumption;

    @Column(name = "km_on_board")
    private Long kmOnBoard;

    @Column(name = "specifications_link")
    private String specificationLink;

    public Long getMachineryId() {
        return machineryId;
    }

    public void setMachineryId(Long machineryId) {
        this.machineryId = machineryId;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public MachineryType getMachineryType() {
        return machineryType;
    }

    public void setMachineryType(MachineryType machineryType) {
        this.machineryType = machineryType;
    }

    public String getMachineryName() {
        return machineryName;
    }

    public void setMachineryName(String machineryName) {
        this.machineryName = machineryName;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public Long getKmOnBoard() {
        return kmOnBoard;
    }

    public void setKmOnBoard(Long kmOnBoard) {
        this.kmOnBoard = kmOnBoard;
    }

    public String getSpecificationLink() {
        return specificationLink;
    }

    public void setSpecificationLink(String specificationLink) {
        this.specificationLink = specificationLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machinery machinery = (Machinery) o;

        if (consumption != null ? !consumption.equals(machinery.consumption) : machinery.consumption != null)
            return false;
        if (garage != null ? !garage.equals(machinery.garage) : machinery.garage != null) return false;
        if (kmOnBoard != null ? !kmOnBoard.equals(machinery.kmOnBoard) : machinery.kmOnBoard != null) return false;
        if (machineryId != null ? !machineryId.equals(machinery.machineryId) : machinery.machineryId != null)
            return false;
        if (machineryName != null ? !machineryName.equals(machinery.machineryName) : machinery.machineryName != null)
            return false;
        if (machineryType != machinery.machineryType) return false;
        if (productionYear != null ? !productionYear.equals(machinery.productionYear)
                : machinery.productionYear != null)
            return false;
        if (specificationLink != null ? !specificationLink.equals(machinery.specificationLink)
                : machinery.specificationLink != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = machineryId != null ? machineryId.hashCode() : 0;
        result = 31 * result + (garage != null ? garage.hashCode() : 0);
        result = 31 * result + (machineryType != null ? machineryType.hashCode() : 0);
        result = 31 * result + (machineryName != null ? machineryName.hashCode() : 0);
        result = 31 * result + (productionYear != null ? productionYear.hashCode() : 0);
        result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
        result = 31 * result + (kmOnBoard != null ? kmOnBoard.hashCode() : 0);
        result = 31 * result + (specificationLink != null ? specificationLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Machinery{" +
                "machineryId=" + machineryId +
                ", garage=" + garage +
                ", machineryType=" + machineryType +
                ", machineryName='" + machineryName + '\'' +
                ", productionYear=" + productionYear +
                ", consumption=" + consumption +
                ", kmOnBoard=" + kmOnBoard +
                ", specificationLink='" + specificationLink + '\'' +
                '}';
    }

    public Machinery() {
    }

    public Machinery(Garage garage, MachineryType machineryType, String machineryName, Integer productionYear,
                     Integer consumption, Long kmOnBoard, String specificationLink) {
        this.garage = garage;
        this.machineryType = machineryType;
        this.machineryName = machineryName;
        this.productionYear = productionYear;
        this.consumption = consumption;
        this.kmOnBoard = kmOnBoard;
        this.specificationLink = specificationLink;
    }
}
