package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNameRepeatException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotExistException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class GroupRepository extends Throwable {
    Map<Integer, Group> studentGroup = new LinkedHashMap<>();

    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        studentGroup.forEach((id, group) -> groups.add(group));
        return groups;
    }

    public void save(List<Group> groupList) {
        groupList.forEach(group -> studentGroup.put(group.getId(), group));
    }

    public Group findById(int groupId) throws GroupNotExistException {
        isExistGroup(groupId);
        return studentGroup.get(groupId);
    }

    public void save(Group group) throws GroupNotExistException {
        isExistGroup(group.getId());
        studentGroup.put(group.getId(), group);
    }

    public void checkGroupNameRepeat(String name) throws GroupNameRepeatException {
        AtomicBoolean flag = new AtomicBoolean(false);
        studentGroup.forEach((id, group) -> {
            if (group.getName().equals(name)){
                flag.set(true);
            }
        });
        if (flag.get()){
            throw new GroupNameRepeatException();
        }
    }
    public void isExistGroup(int id) throws GroupNotExistException {
        if (!studentGroup.containsKey(id)){
            throw new GroupNotExistException();
        }
    }
}
