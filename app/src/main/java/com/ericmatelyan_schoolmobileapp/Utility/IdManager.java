package com.ericmatelyan_schoolmobileapp.Utility;

import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarDB;
import com.ericmatelyan_schoolmobileapp.Database.SchoolCalendarRepo;
import com.ericmatelyan_schoolmobileapp.Entity.CourseEntity;
import com.ericmatelyan_schoolmobileapp.Entity.TermEntity;

import java.util.List;

public abstract class IdManager {
    private static int nextTermId = 0;
    private static int nextCourseId = 0;
    private static int nextAssignmentId = 0;
    private static int nextRequestId = 0;

    public static void setNextTermId(SchoolCalendarRepo repository) {
        List<TermEntity> allTerms = repository.getAllTerms();
        int lastIndex = allTerms.size() - 1;
        TermEntity lastTerm = allTerms.get(lastIndex);
        int lastTermId = lastTerm.getTermId();
        nextTermId = lastTermId;
    }

    public static void setNextCourseId(SchoolCalendarRepo repository) {
        List<CourseEntity> allCourses = repository.getAllCourses();
        int lastIndex = allCourses.size() - 1;
        CourseEntity lastCourse = allCourses.get(lastIndex);
        int lastCourseId = lastCourse.getCourseId();
        nextCourseId = lastCourseId;
    }

    public static int getNextTermId() {
        nextTermId = nextTermId + 1;
        return nextTermId;
    }

    public static int getNextCourseId() {
        nextCourseId = nextCourseId + 1;
        return nextCourseId;
    }

    public static int getNextAssignmentId() {
        nextAssignmentId = nextAssignmentId + 1;
        return nextAssignmentId;
    }

    public static int getNextRequestId() {
        nextRequestId = nextRequestId + 1;
        return nextRequestId;
    }
}
