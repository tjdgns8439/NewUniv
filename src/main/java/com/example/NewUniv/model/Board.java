package com.example.NewUniv.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Board {
    private Integer boardNumber;
    private Long writerId;
    private String writerName;
    private String title;
    private String content;

    public Board(Long writerId, String writerName,  String title, String content) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.content = content;

    }
}
