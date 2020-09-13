package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNameRepeatException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public List<Group> divideGroups() {
        List<Group> groupList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            groupList.add(Group.builder().id(i).name("Team " + i).students(new ArrayList<Student>()).build());
        }
        List<Student> studentList = new ArrayList<>(studentRepository.findAll());
        Collections.shuffle(studentList);
        int groupId = 0;
        while (!studentList.isEmpty()){
            Student student = studentList.remove(studentList.size() - 1);
            groupList.get(groupId).getStudents().add(student);
            groupId = ++groupId % groupList.size();
        }
        groupRepository.save(groupList);
        return groupList;

    }

    public void modifyGroupName(int groupId, String name) throws GroupNameRepeatException, GroupNotExistException {
        Group group = groupRepository.findById(groupId);
        groupRepository.checkGroupNameRepeat(name);
        group.setName(name);
    }
}
