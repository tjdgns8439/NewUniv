package com.example.NewUniv.controller;

import com.example.NewUniv.model.Board;
import com.example.NewUniv.model.Member;
import com.example.NewUniv.repository.BoardRepository;
import com.example.NewUniv.repository.MemoryMemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class boardController {

    BoardRepository boardRepository = BoardRepository.getInstance();

    @GetMapping(value = "/boards/new-form")
    public String createForm() {
        return "boards/createBoard";
    }


    @GetMapping(value = "/boards")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

    //글 내용 불러오기
    @GetMapping(value = "/boards/board")
    public String board(Model model) {

        return "boards/board";
    }

    @PostMapping("/boards/save")
    public String save(HttpServletRequest request, HttpServletResponse response){
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Board board = new Board(id,name,title,content);
        boardRepository.save(board);
        return "index";
    }
}