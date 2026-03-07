package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {


    @Autowired
    private GroupService groupService;

    @PostMapping()
    public Group createGroup(@RequestBody Group group){

        return groupService.createGroup(group);
    }


    @GetMapping()
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id,
                             @RequestBody Group group){

       return groupService.updateGroup(id,group);
    }

    @DeleteMapping("/{id}")
    public Group deleteGroup(@PathVariable Long id){
        return groupService.deleteGroup(id);
    }

}
