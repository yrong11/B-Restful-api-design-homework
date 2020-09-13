package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotExistException;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StudentRepository {
    private static final Map<Integer, Student> studentMap = new HashMap<>();
    private static final AtomicInteger atoInteger = new AtomicInteger(1);

    public void addStudent(Student student) {
        student.setId(atoInteger.get());
        studentMap.put(student.getId(), student);
//        students.add(student);
        atoInteger.set(atoInteger.get() + 1);
    }

    public List<Student> findAll() {
        List<Student> newStudents = new ArrayList<>();
        studentMap.forEach((id, student) -> newStudents.add(student));
        Collections.sort(newStudents);
        return newStudents;
    }

    public List<Student> findAllByGender(GenderType gender) {
        List<Student> newStudents = new ArrayList<>();
        studentMap.forEach((id,student) -> {
            if (student.getGender() == gender)
                newStudents.add(student);
        });
        Collections.sort(newStudents);
        return newStudents;
    }

    public void deleteStudent(int id) throws StudentNotExistException {
        isExistStudent(id);
        studentMap.remove(id);
    }

    public Student getStudentById(Integer id) throws StudentNotExistException {
        isExistStudent(id);
        return studentMap.get(id);
    }

    private void isExistStudent(int id) throws StudentNotExistException {
        if ( !studentMap.containsKey(id)){
            throw new StudentNotExistException();
        }
    }
}
