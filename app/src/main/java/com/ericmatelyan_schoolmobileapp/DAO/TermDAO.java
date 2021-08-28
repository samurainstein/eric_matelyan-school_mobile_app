package com.ericmatelyan_schoolmobileapp.DAO;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = IGNORE)
    void insert(TermEntity term);

    @Update
    void update(TermEntity term);

    @Delete
    void delete(TermEntity term);

    @Query("SELECT * FROM term_table")
    List<TermEntity> getAllTerms();
}
