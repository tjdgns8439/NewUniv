package com.example.NewUniv.repository;

import com.example.NewUniv.model.Board;
import com.example.NewUniv.model.Member;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryTest {
    BoardRepository boardRepository = BoardRepository.getInstance();

    @AfterEach
    public void clear()
    {
        boardRepository.clear();
    }

    @Test
    void save() {
        //given
        Member member1 = new Member(1L,"Member1");

        //when
        Board board = new Board(member1.getStudentId(),member1.getName(),"게시판1","~~~~");
        Board board1 = boardRepository.save(board);

        //then
        Assertions.assertThat(board).isEqualTo(board1);
    }

    @Test
    void multiSave() {
        //given
        Member member1 = new Member(1L,"Member1");
        Member member2 = new Member(2L,"Member2");

        //when
        Board board1 = new Board(member1.getStudentId(),member1.getName(),"게시판1","게시판1 내용");
        Board board2 = new Board(member1.getStudentId(),member1.getName(),"게시판2","게시판2 내용");
        Board board3 = new Board(member2.getStudentId(),member2.getName(),"게시판3","게시판3 내용");

        boardRepository.save(board1);
        boardRepository.save(board2);
        //board3는 저장하지 않음.

        List<Board> boardList = boardRepository.findAll();
        for(Board board : boardList){
            System.out.println(board.toString());//toString은 롬복사용
        }

        //then
        Assertions.assertThat(board1).isIn(boardList);
        Assertions.assertThat(board2).isIn(boardList);
        Assertions.assertThat(board3).isNotIn(boardList);
    }

    @Test
    void findByWriterName(){
        //given
        Member member1 = new Member(1L,"Member1");
        Member member2 = new Member(2L,"Member2");


        Board board1 = new Board(member1.getStudentId(),member1.getName(),"게시판1","게시판1 내용");
        Board board2 = new Board(member1.getStudentId(),member1.getName(),"게시판2","게시판2 내용");
        Board board3 = new Board(member2.getStudentId(),member2.getName(),"게시판3","게시판3 내용");


        boardRepository.save(board1);//Member1
        boardRepository.save(board2);//Member1
        boardRepository.save(board3);//Member2

        //when
        List<Board> boardList = boardRepository.findByWriterName("Member1");

        for(Board board : boardList){
            System.out.println(board.toString());
        }

        //then
        Assertions.assertThat(board1).isIn(boardList);
        Assertions.assertThat(board2).isIn(boardList);
        Assertions.assertThat(board3).isNotIn(boardList);

    }

    @Test
    void findByTitle() {
        //given
        Member member1 = new Member(1L,"Member1");
        Member member2 = new Member(2L,"Member2");

        Board board1 = new Board(member1.getStudentId(),member1.getName(),"게시판1","게시판1 내용");
        Board board2 = new Board(member1.getStudentId(),member1.getName(),"게시판2","게시판2 내용");
        Board board3 = new Board(member2.getStudentId(),member2.getName(),"게시판3","게시판3 내용");


        boardRepository.save(board1);//Member1
        boardRepository.save(board2);//Member1
        boardRepository.save(board3);//Member2


        //when
        List<Board> boardList = boardRepository.findByTitle("게시판"); //제목 부분만 검색해도 다 나오게.

        for(Board board : boardList){
            System.out.println(board.toString());
        }

        //then
        Assertions.assertThat(board1).isIn(boardList);
        Assertions.assertThat(board2).isIn(boardList);
        Assertions.assertThat(board3).isIn(boardList);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member(1L,"Member1");
        Member member2 = new Member(2L,"Member2");

        Board board1 = new Board(member1.getStudentId(),member1.getName(),"게시판1","게시판1 내용");
        Board board2 = new Board(member1.getStudentId(),member1.getName(),"게시판2","게시판2 내용");
        Board board3 = new Board(member2.getStudentId(),member2.getName(),"게시판3","게시판3 내용");


        boardRepository.save(board1);//Member1
        boardRepository.save(board2);//Member1
        boardRepository.save(board3);//Member2


        //when
        List<Board> boardList = boardRepository.findAll(); //제목 부분만 검색해도 다 나오게.

        for(Board board : boardList){
            System.out.println(board.toString());
        }

        //then
        Assertions.assertThat(board1).isIn(boardList);
        Assertions.assertThat(board2).isIn(boardList);
        Assertions.assertThat(board3).isIn(boardList);
    }

    @Test
    void delete() {
        //given
        Member member1 = new Member(1L, "Member1");
        Board board1 = new Board(member1.getStudentId(),member1.getName(),"게시판1","게시판1 내용");
        Board board2 = new Board(member1.getStudentId(),member1.getName(),"게시판2","게시판2 내용");
        Board board3 = new Board(member1.getStudentId(),member1.getName(),"게시판3","게시판3 내용");

        boardRepository.save(board1);//Member1
        boardRepository.save(board2);//Member1
        boardRepository.save(board3);//Member1

        //when
        boardRepository.delete(board1);

        //then
        List<Board> boardList = boardRepository.findAll();

        for(Board board : boardList){
            System.out.println(board.toString());
        }
        assertThat(board1).isNotIn(boardList);
    }

}