package com.example.NewUniv.repository;

import com.example.NewUniv.model.Member;


import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static final MemoryMemberRepository instance = new MemoryMemberRepository();

    public static MemoryMemberRepository getInstance() {
        return instance;
    }

    private MemoryMemberRepository() {
    }

    //<id, Member>
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        //키값이 이미 있으면 null 반환
        if (store.containsKey(member.getStudentId())) {
            System.out.println("이미 존재하는 아이디입니다.");
            return null;
        }
        else {
            store.put(member.getStudentId(), member);
            return member;
        }
    }

    @Override
    public Optional<Member> findById(Long Id) {
        return store.values().stream()
                .filter(member -> member.getStudentId().equals(Id))
                .findAny();
    }

    @Override
    public void delete(Member member) {
        store.remove(member.getStudentId());
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clear(){
        store.clear();
    }
}
