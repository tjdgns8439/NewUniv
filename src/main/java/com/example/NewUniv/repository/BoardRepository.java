package com.example.NewUniv.repository;

import com.example.NewUniv.model.Board;
import com.example.NewUniv.model.Member;

import java.util.*;

public class BoardRepository {
    private static final BoardRepository instance = new BoardRepository();
    private static Integer id = 0;

    private BoardRepository() {
    }

    public static BoardRepository getInstance() {
        return instance;
    }

    //<id, Member>
    private static Map<Integer, Board> store = new HashMap<>();

    public Board save(Board board) {
        board.setBoardNumber(++id);
        board.setWriterId(board.getWriterId());
        board.setWriterName(board.getWriterName());
        board.setTitle(board.getTitle());
        board.setContent(board.getContent());

        store.put(board.getBoardNumber(), board);
        return board;
    }

    public List<Board> findByWriterName(String writerName){
        return new ArrayList<Board>(store.values().stream()
                .filter(board -> board.getWriterName().equals(writerName)).toList());
    }

    public List<Board> findByTitle(String title) {
        return new ArrayList<Board>(store.values().stream()
                .filter(board -> board.getTitle().contains(title)).toList());
    }

    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Board board) {
        store.remove(board.getBoardNumber());
    }

    //테스트
    public void clear(){
        store.clear();
    }
}
