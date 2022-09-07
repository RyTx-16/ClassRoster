package com.sg.classroster;

import com.sg.classroster.DAO.CourseDao;
import com.sg.classroster.DAO.StudentDao;
import com.sg.classroster.DAO.TeacherDao;
import com.sg.classroster.Objects.Course;
import com.sg.classroster.Objects.Student;
import com.sg.classroster.Objects.Teacher;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ryan Taylor
 * @created 24/08/2022 - 01:50
 */
@SpringBootTest
public class CourseDaoDBTest {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    public CourseDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        for (Teacher teacher : teachers) {
            teacherDao.deleteTeacherById(teacher.getId());
        }

        List<Student> students = studentDao.getAllStudents();
        for (Student student : students) {
            studentDao.deleteStudentById(student.getId());
        }

        List<Course> courses = courseDao.getAllCourses();
        for (Course course : courses) {
            courseDao.deleteCourseById(course.getId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetCourse() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test Teacher First");
        teacher.setLastName("Test Teacher Last");
        teacher.setSpeciality("Test Teacher Specialty");
        teacher = teacherDao.addTeacher(teacher);
        //Teacher fromDao1 = teacherDao.getTeacherById(teacher.getId());

        //assertEquals(teacher, fromDao1);

        Student student = new Student();
        student.setFirstName("Test Student First");
        student.setLastName("Test Student Last");
        student = studentDao.addStudent(student);

        List<Student> students = new ArrayList<>();
        students.add(student);

        Course course = new Course();
        course.setName("Test Course Name");
        course.setTeacher(teacher);
        course.setStudents(students);
        course = courseDao.addCourse(course);

        Course fromDao = courseDao.getCourseById(course.getId());
        assertEquals(course, fromDao);
    }

}