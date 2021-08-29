package com.ericmatelyan_schoolmobileapp.Database;

import android.app.Application;

import com.ericmatelyan_schoolmobileapp.DAO.AssignmentDAO;
import com.ericmatelyan_schoolmobileapp.DAO.CourseDAO;
import com.ericmatelyan_schoolmobileapp.DAO.TermDAO;
import com.ericmatelyan_schoolmobileapp.Entity.AssignmentEntity;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchoolCalendarRepo {
    private TermDAO termDao;
    private CourseDAO courseDao;
    private AssignmentDAO assignmentDAO;

    private List<TermEntity> allTerms;
    private List<CourseEntity> allCourses;
    private List<AssignmentEntity> allAssignments;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public SchoolCalendarRepo(Application application) {
        SchoolCalendarDB database = SchoolCalendarDB.getDatabase(application);
        termDao = database.termDAO();
        courseDao = database.courseDAO();
        assignmentDAO = database.assignmentDAO();
    }

    public List<TermEntity> getAllTerms() {
        databaseExecutor.execute(()-> {
            allTerms = termDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allTerms;
    }

    public void insert(TermEntity term) {
        databaseExecutor.execute(()-> {
            termDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<CourseEntity> getAllCourses() {
        databaseExecutor.execute(()-> {
            allCourses = courseDao.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    public void insert(CourseEntity course) {
        databaseExecutor.execute(()-> {
            courseDao.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<AssignmentEntity> getAllAssignments() {
        databaseExecutor.execute(()-> {
            allAssignments = assignmentDAO.getAllAssignments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssignments;
    }

    public void insert(AssignmentEntity assignment) {
        databaseExecutor.execute(()-> {
            assignmentDAO.insert(assignment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
