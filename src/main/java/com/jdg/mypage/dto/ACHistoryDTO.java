package com.jdg.mypage.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ACHistoryDTO {
    private String key;
    private Timestamp date;
    private String type;
    private String payment;
    private String account;
    private String from;
    private String to;
    private int amount;
    private String title;
    private ACDetailsDTO[] details;
}
