package com.example.NewUniv.repository;

import com.example.NewUniv.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //리퍼지토리에 저장
    Member save(Member member);

    //리퍼지토리 내부에 있는 멤버 조회
    Optional<Member> findById(Long Id);

    //삭제
    void delete(Member member);

    //전체 조회
    List<Member> findAll();

    //테스트
    void clear();
}
