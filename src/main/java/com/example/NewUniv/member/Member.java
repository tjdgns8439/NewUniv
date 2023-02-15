package com.example.NewUniv.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long studentId;
    private String name;

    public Member(Long studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
}
