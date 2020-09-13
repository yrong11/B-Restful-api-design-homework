package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student){
        this.studentRepository.addStudent(student);
    }

    public List<Student> getStudents(GenderType gender) {
        return gender == null ? studentRepository.findAll() : studentRepository.findAllByGender(gender);
    }

    public void deleteStudent(Integer id) throws StudentNotExistException {
        studentRepository.deleteStudent(id);
    }
}
