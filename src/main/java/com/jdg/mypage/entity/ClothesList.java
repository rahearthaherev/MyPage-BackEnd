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
public class ClothesList {
    @Column
    private String type;
    @Column
    private String name;
    @Column
    @Id
    private int index;
}
