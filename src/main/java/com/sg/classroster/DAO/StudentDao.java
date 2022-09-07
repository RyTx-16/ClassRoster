package com.sg.classroster.DAO;

import com.sg.classroster.Objects.Student;

import java.util.List;

/**
 * @author Ryan Taylor
 * @created 24/08/2022 - 01:41
 */
public interface StudentDao {
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
}
