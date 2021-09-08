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

    public SchoolCalendarRepo(Application application) {
        SchoolCalendarDB database = SchoolCalendarDB.getDatabase(application);
        termDao = database.termDAO();
        courseDao = database.courseDAO();
        assignmentDAO = database.assignmentDAO();
    }

    public List<TermEntity> getAllTerms() {
        SchoolCalendarDB.databaseExecutor.execute(()-> {
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
        SchoolCalendarDB.databaseExecutor.execute(()-> {
            termDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(TermEntity term) {
        SchoolCalendarDB.databaseExecutor.execute(() -> {
            termDao.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(TermEntity term) {
        SchoolCalendarDB.databaseExecutor.execute(() -> {
            termDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<CourseEntity> getAllCourses() {
        SchoolCalendarDB.databaseExecutor.execute(()-> {
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
        SchoolCalendarDB.databaseExecutor.execute(()-> {
            courseDao.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(CourseEntity course) {
        SchoolCalendarDB.databaseExecutor.execute(() -> {
            courseDao.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(CourseEntity course) {
        SchoolCalendarDB.databaseExecutor.execute(() -> {
            courseDao.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<AssignmentEntity> getAllAssignments() {
        SchoolCalendarDB.databaseExecutor.execute(()-> {
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
        SchoolCalendarDB.databaseExecutor.execute(()-> {
            assignmentDAO.insert(assignment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
