package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentNotExistException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StudentRepository {
    private String[] peopleNames = {"沈乐棋", "徐慧慧", "陈思聪", "王江林", "王登宇", "杨思雨", "江雨舟", "廖淼",
            "胡晓", "但杰", "盖迈达", "肖美琦", "黄云洁", "齐瑾浩", "刘亮亮", "肖逸凡", "王作文", "郭瑞凌",
            "李明豪", "党泽", "肖伊佐", "贠晨曦", "李康宁", "马庆", "商婕", "余榕", "谌哲", "董翔锐", "陈泰宇",
            "赵允齐", "张柯", "廖文强", "刘轲", "廖浚斌", "凌凤仪"};

    private static final Map<Integer, Student> studentMap = new HashMap<>();
    private static final AtomicInteger atoInteger = new AtomicInteger(1);

    @PostConstruct
    private void initData() {
       for (String name : peopleNames) {
           Student student = Student.builder().name(name).gender(GenderType.FEMALE).build();
           saveStudent(student);
       }
    }
    public void saveStudent(Student student) {
        if (student.getId() == 0 || !studentMap.containsKey(student.getId())){
            student.setId(atoInteger.get());
            studentMap.put(student.getId(), student);
            atoInteger.set(atoInteger.get() + 1);
        }else{
            studentMap.put(student.getId(), student);
        }

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
