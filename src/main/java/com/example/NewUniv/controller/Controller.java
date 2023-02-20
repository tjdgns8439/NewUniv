package com.example.NewUniv.controller;

import com.example.NewUniv.member.Member;
import com.example.NewUniv.member.MemberForm;
import com.example.NewUniv.repository.MemoryMemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    MemoryMemberRepository memberRepository = MemoryMemberRepository.getInstance();

    @GetMapping(value = "/members/new-form")
    public String createForm(MemberForm form) {
        Member member = new Member(form.getId(), form.getName());
        memberRepository.save(member);
        return "members/createMember";
    }


    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @PostMapping("/members/save")
    public String save(HttpServletRequest request, HttpServletResponse response){
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        Member member = new Member(id, name);
        memberRepository.save(member);

        return "index";
    }
}
