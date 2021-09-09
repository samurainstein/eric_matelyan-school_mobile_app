package com.ericmatelyan_schoolmobileapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "assignment_table")
public class AssignmentEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int assignmentId;
    private String assignmentType;
    private String assignmentName;
    private Date startDate;
    private Date endDate;

    public AssignmentEntity(int assignmentId, String assignmentType, String assignmentName, Date startDate, Date endDate) {
        this.assignmentId = assignmentId;
        this.assignmentType = assignmentType;
        this.assignmentName = assignmentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AssignmentEntity{" +
                "assignmentId=" + assignmentId +
                ", assignmentType='" + assignmentType + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
