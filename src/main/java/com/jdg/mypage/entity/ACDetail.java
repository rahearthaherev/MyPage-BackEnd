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
public class ACDetail {
    @Column
    private String key;
    @Column
    @Id
    private String subKey;
    @Column
    private String category;
    @Column
    private String description;
    @Column
    private int amount;
}
