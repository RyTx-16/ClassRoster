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

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ryan Taylor
 * @created 24/08/2022 - 01:50
 */
@SpringBootTest
public class TeacherDBTest {

    @Autowired
    TeacherDao teacherDao;

    public TeacherDBTest() {
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
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test First");
        teacher.setLastName("Test Last");
        teacher.setSpeciality("Test Specialty");
        teacher = teacherDao.addTeacher(teacher);

        Teacher fromDao = teacherDao.getTeacherById(teacher.getId());

        assertEquals(teacher, fromDao);


    }

}