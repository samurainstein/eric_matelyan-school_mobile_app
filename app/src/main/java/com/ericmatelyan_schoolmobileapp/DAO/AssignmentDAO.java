package com.ericmatelyan_schoolmobileapp.DAO;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Insert(onConflict = IGNORE)
    void insert(AssignmentEntity assignment);

    @Update
    void update(AssignmentEntity assignment);

    @Delete
    void delete(AssignmentEntity assignment);

    @Query("SELECT * FROM assignment_table")
    List<AssignmentEntity> getAllAssignments();
}
