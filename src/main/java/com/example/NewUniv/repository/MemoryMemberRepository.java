package com.example.NewUniv.repository;

import com.example.NewUniv.member.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //<id, Member>
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        //키값이 이미 있으면 null 반환
        if (store.containsKey(member.getStudentId())) {
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
                .findAny()
                .filter(member -> member.getStudentId().equals(Id));
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
