package com.ericmatelyan_schoolmobileapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ericmatelyan_schoolmobileapp.DAO.AssignmentDAO;
import com.ericmatelyan_schoolmobileapp.DAO.CourseDAO;
import com.ericmatelyan_schoolmobileapp.DAO.TermDAO;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import com.ericmatelyan_schoolmobileapp.Utility.DateConverter;

@Database(entities = {TermEntity.class, CourseEntity.class, AssignmentEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SchoolCalendarDB extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssignmentDAO assignmentDAO();

    private static volatile SchoolCalendarDB INSTANCE;

    static SchoolCalendarDB getDatabase(final Context context) {
        if(INSTANCE==null) {
            synchronized (SchoolCalendarDB.class) {
                if(INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchoolCalendarDB.class, "School Calendar DB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
