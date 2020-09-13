package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public ResponseEntity addStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity getStudents(@RequestParam(required = false) GenderType gender){
        return ResponseEntity.ok(studentService.getStudents(gender));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) throws StudentNotExistException {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getStudent(@PathVariable Integer id) throws StudentNotExistException {
        return  ResponseEntity.ok(studentService.getStudent(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity updataStudent(@PathVariable int id, @RequestBody Student student) throws StudentNotExistException {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok().build();
    }

}
