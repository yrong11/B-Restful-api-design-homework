package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNameRepeatException;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotExistException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public ResponseEntity getGroups(){
        return ResponseEntity.ok(groupService.getGroups());
    }

    @GetMapping("/divide")
    public ResponseEntity divideGroup() {
        return ResponseEntity.ok(groupService.divideGroups());
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity modifyGroupName(@PathVariable int groupId, @RequestBody String name) throws GroupNameRepeatException, GroupNotExistException {
        groupService.modifyGroupName(groupId, name);
        return ResponseEntity.ok().build();
    }
}
