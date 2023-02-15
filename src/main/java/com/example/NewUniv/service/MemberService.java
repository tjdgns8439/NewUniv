package com.example.NewUniv.service;


import com.example.NewUniv.member.Member;
import com.example.NewUniv.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
