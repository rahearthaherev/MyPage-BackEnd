package com.jdg.mypage.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardDTO {
    private String board_key;
    private String title;
    private String author;
    private String create_time;
    private String modified_time;
    private String menu_sub_key;
    private String search;
    private String content;
}
