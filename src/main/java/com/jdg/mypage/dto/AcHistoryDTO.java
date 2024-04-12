package com.jdg.mypage.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AcHistoryDTO {
    private String key;
    private Timestamp date;
    private String type;
    private String payment;
    private String account;
    private String beforeAccount;
    private String afterAccount;
    private int amount;
    private String title;
    private AcDetailsDTO[] details;
}
