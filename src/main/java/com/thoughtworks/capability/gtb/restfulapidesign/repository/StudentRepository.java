package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StudentRepository {
    private static final List<Student> students = new ArrayList<>();
    private static final AtomicInteger atoInteger = new AtomicInteger(1);

    public void addStudent(Student student) {
        student.setId(atoInteger.get());
        students.add(student);
        atoInteger.set(atoInteger.get() + 1);
    }

    public List<Student> findAll() {
        Collections.sort(students);
        return students;
    }

    public List<Student> findAllByGender(GenderType gender) {
        List<Student> newStudents = new ArrayList<>();
        students.forEach((student) -> {
            if (student.getGender() == gender)
                newStudents.add(student);
        });
        return newStudents;
    }

}
