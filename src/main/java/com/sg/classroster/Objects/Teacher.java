package com.sg.classroster.Objects;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Teacher {

    private int id;
    @NotBlank(message = "First name cannot be empty.")
    @Size(max = 30, message = "First name must be less than 30 characters.")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty.")
    @Size(max = 50, message = "Last name must be less than 50 characters.")
    private String lastName;
    @Size(max = 50, message = "Speciality name must be less than 50 characters.")
    private String speciality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(speciality, teacher.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, speciality);
    }
}
