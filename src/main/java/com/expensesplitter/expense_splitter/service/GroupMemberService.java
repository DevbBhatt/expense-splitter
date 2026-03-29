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
import java.util.Optional;

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
        if(group.isDeleted()) throw new RuntimeException("Group is deleted");

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));
        if(user.isDeleted()) throw new RuntimeException("User is deleted");

        Optional<GroupMember> existsMember = groupMemberRepository
                .findByGroupIdAndUserId(groupId, userId);

        if(existsMember.isPresent()){
            GroupMember member = existsMember.get();

            if(!member.isDeleted()){
                throw new RuntimeException("User Already in Group");
            }

            member.setDeleted(false);
            member.setJoinedAt(LocalDateTime.now());
            return groupMemberRepository.save(member);
        }


        // New Member
        GroupMember member = new GroupMember();

        member.setUser(user);
        member.setGroup(group);
        member.setJoinedAt(LocalDateTime.now());

        return groupMemberRepository.save(member);
    }


    public List<GroupMember> getGroupMembers(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("Group Not Found"));
        if(group.isDeleted()) throw new RuntimeException("Group is Deleted");

        return groupMemberRepository.findByGroup(group)
                .stream().filter(m -> !m.isDeleted()).toList();
    }

    public void removeMemberFromGroup(Long groupId,Long userId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("Group Not Found"));

        if(group.isDeleted()) throw new RuntimeException("Group is Deleted");

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        if(user.isDeleted()) throw new RuntimeException("User is Deleted");


        GroupMember member = groupMemberRepository
                .findByGroupAndUser(group,user).orElseThrow(()->new RuntimeException("Member Not Found"));

        if(member.isDeleted()){
            throw new RuntimeException("Member already removed");
        }

        member.setDeleted(true);
        groupMemberRepository.save(member);
    }
}
