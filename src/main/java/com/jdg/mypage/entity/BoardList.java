package com.jdg.mypage.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardList {
    @Id
    @Column
    private String board_key;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private Timestamp create_time;
    @Column
    private Timestamp modified_time;
    @Column
    private String menu_sub_key;
    @Column
    private String content;


}
