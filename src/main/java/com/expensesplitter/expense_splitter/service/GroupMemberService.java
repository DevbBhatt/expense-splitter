package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.GroupMember;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.GroupMemberRepository;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public GroupMember addMemberToGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new RuntimeException("Group Not Found"));

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        GroupMember member = new GroupMember();

        member.setUser(user);
        member.setGroup(group);
        member.setJoinedAt(LocalDateTime.now());

        return groupMemberRepository.save(member);
    }


    public List<GroupMember> getGroupMembers(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("Group Not Found"));

        return groupMemberRepository.findByGroup(group);
    }

    public void removeMemberFromGroup(Long groupId,Long userId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("Group Not Found"));

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        GroupMember member = groupMemberRepository
                .findByGroupAndUser(group,user).orElseThrow(()->new RuntimeException("Member Not Found"));

         groupMemberRepository.delete(member);
    }
}
