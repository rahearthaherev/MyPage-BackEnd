package com.jdg.mypage.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ACHistory {
    @Column
    @Id
    private String key;
    @Column
    private Timestamp date;
    @Column
    private String type;
    @Column
    private String payment;
    @Column
    private String account;
    @Column
    private String from;
    @Column
    private String to;
    @Column
    private int amount;
    @Column
    private String title;
}
