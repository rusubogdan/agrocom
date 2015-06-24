package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "society_id")
    private Society society;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_value")
    private String paymentValue;

    @Column(name = "payment_date")
    private Timestamp paymentDate;


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(String paymentValue) {
        this.paymentValue = paymentValue;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
//                "society=" + society +
//                ", tenant=" + tenant +
                ", paymentType='" + paymentType + '\'' +
                ", paymentValue='" + paymentValue + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
