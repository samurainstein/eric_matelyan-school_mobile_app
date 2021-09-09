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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TermEntity.class, CourseEntity.class, AssignmentEntity.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class SchoolCalendarDB extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssignmentDAO assignmentDAO();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


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
