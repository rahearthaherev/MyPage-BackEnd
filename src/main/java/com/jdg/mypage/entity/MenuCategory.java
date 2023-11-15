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
public class MenuCategory {
    @Id
    @Column
    private String detail_key;
    @Column
    private int index;
    @Column
    private String menu_key;
    @Column
    private String menu_type;
}
