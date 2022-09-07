package com.sg.classroster.DAO;

import com.sg.classroster.Objects.Course;
import com.sg.classroster.Objects.Student;
import com.sg.classroster.Objects.Teacher;

import java.util.List;

/**
 * @author Ryan Taylor
 * @created 24/08/2022 - 01:41
 */
public interface CourseDao {
    Course getCourseById(int id);
    List<Course> getAllCourses();
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);

    List<Course> getCoursesForTeacher(Teacher teacher);
    List<Course> getCoursesForStudent(Student student);
}
