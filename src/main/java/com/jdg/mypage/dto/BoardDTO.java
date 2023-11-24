package com.jdg.mypage.dto;

import com.jdg.mypage.entity.BoardList;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardDTO {
    private String board_key;
    private String title;
    private String author;
    private Timestamp create_time;
    private Timestamp modified_time;
    private String menu_sub_key;
    private String search;
    private String content;

    public BoardList toBoardList() {
        BoardList boardList = new BoardList();
        boardList.setBoard_key(board_key);
        boardList.setTitle(title);
        boardList.setAuthor(author);
        boardList.setMenu_sub_key(menu_sub_key);
        boardList.setContent(content);
        boardList.setCreate_time(create_time);
        boardList.setModified_time(modified_time);
        return boardList;
    }
}
