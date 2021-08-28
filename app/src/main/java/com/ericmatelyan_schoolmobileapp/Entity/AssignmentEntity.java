package com.ericmatelyan_schoolmobileapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "assignment_table")
public class AssignmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assignmentId;
    private String assignmentType;
    private String assignmentTitle;
    private LocalDate startDate;
    private LocalDate endDate;

    public AssignmentEntity(int assignmentId, String assignmentType, String assignmentTitle, LocalDate startDate, LocalDate endDate) {
        this.assignmentId = assignmentId;
        this.assignmentType = assignmentType;
        this.assignmentTitle = assignmentTitle;
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

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AssignmentEntity{" +
                "assignmentId=" + assignmentId +
                ", assignmentType='" + assignmentType + '\'' +
                ", assignmentTitle='" + assignmentTitle + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
