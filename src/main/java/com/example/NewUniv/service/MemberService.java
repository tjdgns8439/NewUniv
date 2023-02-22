package com.example.NewUniv.service;


import com.example.NewUniv.model.Member;
import com.example.NewUniv.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> join(Member member) {
        if(memberRepository.findById(member.getStudentId()).equals(Optional.empty())){
            return Optional.empty();
        }
        else return Optional.ofNullable(memberRepository.save(member));
    }

}
