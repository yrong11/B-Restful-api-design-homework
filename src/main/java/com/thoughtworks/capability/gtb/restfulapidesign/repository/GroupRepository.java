package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GroupRepository {
    Map<Integer, Group> studentGroup = new LinkedHashMap<>();

    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        studentGroup.forEach((id, group) -> groups.add(group));
        return groups;
    }
}
