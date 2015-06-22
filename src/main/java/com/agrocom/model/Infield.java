package com.agrocom.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "infield")
public class Infield implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator = "infield_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "infield_id_seq", sequenceName = "infield_id_seq", allocationSize = 1)
    @Column(name = "infield_id")
    private Long infieldId;

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

    @Column(name = "area_ha")
    private Double areaHa;

    public Double getAreaHa() {
        return areaHa;
    }

    public void setAreaHa(Double areaHa) {
        this.areaHa = areaHa;
    }

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

    public String getLandLordFullName() {
        return landLordFullName;
//        return tenant == null ? "" : tenant.getFirstName() + tenant.getLastName();
    }

    public void setLandLordFullName(String landLordFullName) {
        this.landLordFullName = landLordFullName;
    }

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private String landLordFullName;

    public Infield(Infield other) {
        this.infieldId = other.infieldId;
        this.society = other.society;
        this.tenant = other.tenant;
        this.locationCode = other.locationCode;
        this.areaHa = other.areaHa;
        this.county = other.county;
        this.village = other.village;
        this.fiveYearsAgo = other.fiveYearsAgo;
        this.fourYearsAgo = other.fourYearsAgo;
        this.threeYearsAgo = other.threeYearsAgo;
        this.twoYearsAgo = other.twoYearsAgo;
        this.lastYear = other.lastYear;
    }

    public Long getInfieldId() {
        return infieldId;
    }

    public void setInfieldId(Long infieldId) {
        this.infieldId = infieldId;
    }

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getFiveYearsAgo() {
        return fiveYearsAgo;
    }

    public void setFiveYearsAgo(String fiveYearsAgo) {
        this.fiveYearsAgo = fiveYearsAgo;
    }

    public String getFourYearsAgo() {
        return fourYearsAgo;
    }

    public void setFourYearsAgo(String fourYearsAgo) {
        this.fourYearsAgo = fourYearsAgo;
    }

    public String getThreeYearsAgo() {
        return threeYearsAgo;
    }

    public void setThreeYearsAgo(String threeYearsAgo) {
        this.threeYearsAgo = threeYearsAgo;
    }

    public String getTwoYearsAgo() {
        return twoYearsAgo;
    }

    public void setTwoYearsAgo(String twoYearsAgo) {
        this.twoYearsAgo = twoYearsAgo;
    }

    public String getLastYear() {
        return lastYear;
    }

    public void setLastYear(String lastYear) {
        this.lastYear = lastYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Infield infield = (Infield) o;

        if (county != null ? !county.equals(infield.county) : infield.county != null) return false;
        if (fiveYearsAgo != null ? !fiveYearsAgo.equals(infield.fiveYearsAgo) : infield.fiveYearsAgo != null)
            return false;
        if (fourYearsAgo != null ? !fourYearsAgo.equals(infield.fourYearsAgo) : infield.fourYearsAgo != null)
            return false;
        if (infieldId != null ? !infieldId.equals(infield.infieldId) : infield.infieldId != null) return false;
        if (lastYear != null ? !lastYear.equals(infield.lastYear) : infield.lastYear != null) return false;
        if (locationCode != null ? !locationCode.equals(infield.locationCode) : infield.locationCode != null)
            return false;
//        if (society != null ? !society.equals(infield.society) : infield.society != null) return false;
//        if (tenant != null ? !tenant.equals(infield.tenant) : infield.tenant != null) return false;
        if (threeYearsAgo != null ? !threeYearsAgo.equals(infield.threeYearsAgo) : infield.threeYearsAgo != null)
            return false;
        if (twoYearsAgo != null ? !twoYearsAgo.equals(infield.twoYearsAgo) : infield.twoYearsAgo != null) return false;
        if (village != null ? !village.equals(infield.village) : infield.village != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = infieldId != null ? infieldId.hashCode() : 0;
//        result = 31 * result + (society != null ? society.hashCode() : 0);
//        result = 31 * result + (tenant != null ? tenant.hashCode() : 0);
        result = 31 * result + (locationCode != null ? locationCode.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (village != null ? village.hashCode() : 0);
        result = 31 * result + (fiveYearsAgo != null ? fiveYearsAgo.hashCode() : 0);
        result = 31 * result + (fourYearsAgo != null ? fourYearsAgo.hashCode() : 0);
        result = 31 * result + (threeYearsAgo != null ? threeYearsAgo.hashCode() : 0);
        result = 31 * result + (twoYearsAgo != null ? twoYearsAgo.hashCode() : 0);
        result = 31 * result + (lastYear != null ? lastYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Infield{" +
                "infieldId=" + infieldId +
                ", society=" + society +
                ", tenant=" + tenant +
                ", locationCode='" + locationCode +
                ", landLordFullName='" + landLordFullName +
                ", county='" + county +
                ", village='" + village +
                ", fiveYearsAgo='" + fiveYearsAgo +
                ", fourYearsAgo='" + fourYearsAgo +
                ", threeYearsAgo='" + threeYearsAgo +
                ", twoYearsAgo='" + twoYearsAgo +
                ", lastYear='" + lastYear +
                '}';
    }

    public Infield() {
    }

    public Infield(Society society, User tenant, String locationCode, Double areaHa, String county, String village,
                   String fiveYearsAgo, String fourYearsAgo, String threeYearsAgo, String twoYearsAgo,
                   String lastYear) {
        this.society = society;
        this.tenant = tenant;
        this.locationCode = locationCode;
        this.areaHa = areaHa;
        this.county = county;
        this.village = village;
        this.fiveYearsAgo = fiveYearsAgo;
        this.fourYearsAgo = fourYearsAgo;
        this.threeYearsAgo = threeYearsAgo;
        this.twoYearsAgo = twoYearsAgo;
        this.lastYear = lastYear;
    }
}
