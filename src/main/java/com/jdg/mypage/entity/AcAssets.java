package com.jdg.mypage.entity;

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
public class AcAssets {
    @Id
    @Column
    private String account;
    @Column
    private int amount;
    @Column
    private String type;
}
