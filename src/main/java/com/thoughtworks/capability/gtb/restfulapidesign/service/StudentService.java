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

    public void saveStudent(Student student){
        this.studentRepository.saveStudent(student);
    }

    public List<Student> getStudents(GenderType gender) {
        return gender == null ? studentRepository.findAll() : studentRepository.findAllByGender(gender);
    }

    public void deleteStudent(Integer id) throws StudentNotExistException {
        studentRepository.deleteStudent(id);
    }

    public Student getStudent(Integer id) throws StudentNotExistException {
        return studentRepository.getStudentById(id);
    }

    public void updateStudent(int id, Student student) throws StudentNotExistException {
        Student oldStudent = studentRepository.getStudentById(id);
        if ( student.getGender() != null ) oldStudent.setGender(student.getGender());
        if ( student.getName() != null ) oldStudent.setName(student.getName());
        if ( student.getNote() != null ) oldStudent.setNote(student.getNote());
        studentRepository.saveStudent(oldStudent);
    }
}
