package com.agrocom.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "work_history")
public class WorkHistory implements Serializable {

    private static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(generator = "work_history_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "work_history_id_seq", sequenceName = "work_history_id_seq", allocationSize = 1)
    @Column(name = "work_his_id")
    private Long workHistoryId;

    // unidirectional relation between this entity and its fields

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

    @Column(name = "status")
    private String status;

    public Long getWorkHistoryId() {
        return workHistoryId;
    }

    public void setWorkHistoryId(Long workHistoryId) {
        this.workHistoryId = workHistoryId;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public Infield getInfield() {
        return infield;
    }

    public void setInfield(Infield infield) {
        this.infield = infield;
    }

    public Machinery getMachinery() {
        return machinery;
    }

    public void setMachinery(Machinery machinery) {
        this.machinery = machinery;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkHistory that = (WorkHistory) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (infield != null ? !infield.equals(that.infield) : that.infield != null) return false;
        if (machinery != null ? !machinery.equals(that.machinery) : that.machinery != null) return false;
        if (workHistoryId != null ? !workHistoryId.equals(that.workHistoryId) : that.workHistoryId != null)
            return false;
        if (workType != that.workType) return false;
        if (worker != null ? !worker.equals(that.worker) : that.worker != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workHistoryId != null ? workHistoryId.hashCode() : 0;
        result = 31 * result + (worker != null ? worker.hashCode() : 0);
        result = 31 * result + (infield != null ? infield.hashCode() : 0);
        result = 31 * result + (machinery != null ? machinery.hashCode() : 0);
        result = 31 * result + (workType != null ? workType.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkHistory{" +
                "workHistoryId=" + workHistoryId +
                ", worker=" + worker +
                ", infield=" + infield +
                ", machinery=" + machinery +
                ", workType=" + workType +
                ", date=" + date +
                ", description='" + description +
                ", duration=" + duration +
                ", status='" + status +
                '}';
    }

    public WorkHistory() {
    }

    public WorkHistory(User worker, Infield infield, Machinery machinery, WorkType workType, Timestamp date,
                       String description, Integer duration, String status) {
        this.worker = worker;
        this.infield = infield;
        this.machinery = machinery;
        this.workType = workType;
        this.date = date;
        this.description = description;
        this.duration = duration;
        this.status = status;
    }

}
