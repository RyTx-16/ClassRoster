package com.sg.classroster.Controller;

import com.sg.classroster.DAO.TeacherDao;
import com.sg.classroster.Objects.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Controller
public class Controller {
    Set<ConstraintViolation<Teacher>> violations = new HashSet<>();

    @Autowired
    TeacherDao teacherDao;

    @GetMapping("teachers")
    public String displayTeachers(Model model) {
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("teachers", teachers);

        model.addAttribute("errors", violations);

        return "teachers";
    }


    @PostMapping("addTeacher")
    public String addTeacher(HttpServletRequest request) {
        /*
        1. Grab the information from the web page form
         */
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialty = request.getParameter("specialty");

        /*
        2. Create a Teacher object and populate relevant values
         */
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setSpeciality(specialty);
        /*
        3. Talks to the DAO object to update the DB
         */
        //teacherDao.addTeacher(teacher);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(teacher);

        if(violations.isEmpty()) {
            teacherDao.addTeacher(teacher);
        }



        /*
        4. Reload the page
         */
        return "redirect:/teachers";
    }

    @GetMapping("deleteTeacher")
    public String deleteTeacher(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        teacherDao.deleteTeacherById(id);

        return "redirect:/teachers";
    }

    @GetMapping("editTeacher")
    public String editTeacher(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDao.getTeacherById(id);

        model.addAttribute("teacher", teacher);
        return "editTeacher";
    }


    @PostMapping("editTeacher")
    public String performEditTeacher(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDao.getTeacherById(id);

        teacher.setFirstName(request.getParameter("firstName"));
        teacher.setLastName(request.getParameter("lastName"));
        teacher.setSpeciality(request.getParameter("specialty"));

        teacherDao.updateTeacher(teacher);

        return "redirect:/teachers";
    }


}
