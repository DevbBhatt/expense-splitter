package com.expensesplitter.expense_splitter.service;


import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;


    public Group createGroup(Group group) {
        group.setCreatedAt(LocalDateTime.now());
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(()-> new RuntimeException("Group Not Found"));
    }

    public Group updateGroup(Long id, Group group) {
        Group group1 = groupRepository.findById(id).orElseThrow(()-> new RuntimeException("Group Not Found"));

        group1.setName(group.getName());
        return groupRepository.save(group1);
    }

    public Group deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Not found"));

        groupRepository.delete(group);
        return group;

    }
}
