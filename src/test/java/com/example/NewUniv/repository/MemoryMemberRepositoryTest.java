package com.example.NewUniv.repository;

import com.example.NewUniv.member.Member;
import com.example.NewUniv.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {



    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void clareStore(){
        memberRepository.clear();
    }

    @Test
    void save() {
        //given

        Member member1 = new Member(1L, "aaa");
        Member member2 = new Member(1L, "bbb");

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        Member result = memberRepository.findById(member1.getStudentId()).get();
        List<Member> memberList = memberRepository.findAll();
        for(Member member : memberList){
            System.out.println("ID= " + member.getStudentId() +", name= " +member.getName());
        }

        assertThat(result).isEqualTo(member1);
    }


    @Test
    void findById() {
        //given
        Member member1 = new Member(1L,"aaa");
        Member member2 = new Member(2L,"bbb");
        Member member3 = new Member(3L,"ccc");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> memberList = memberRepository.findAll();
        for(Member member : memberList){
            System.out.println("ID= " + member.getStudentId() +", name= " +member.getName());
        }

        //when
        Member result = memberRepository.findById(3L).get();

        //then
        assertThat(result).isEqualTo(member3);
    }

    @Test
    void delete() {
        //given
        Member member1 = new Member(1L, "as");
        Member member2 = new Member(2L, "as");
        memberRepository.save(member1);
        memberRepository.save(member2);


        //when
        memberRepository.delete(member1);
//        memberRepository.delete(member2);

        //then
        List<Member> memberList = memberRepository.findAll();
        for(Member member : memberList){
            System.out.println("ID= " + member.getStudentId() +", name= " +member.getName());
        }
        assertThat(member1).isNotIn(memberList);

    }


    @Test
    void findAll() {
        //given
        Member member1 = new Member(1L, "as");
        Member member2 = new Member(2L, "as");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
    }
}