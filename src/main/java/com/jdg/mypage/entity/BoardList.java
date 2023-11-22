package com.jdg.mypage.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardList {
    @Column
    private int index;
    @Id
    @Column
    private String board_key;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String create_time;
    @Column
    private String modified_time;
    @Column
    private String menu_sub_key;
    @Column
    private String content;
}
