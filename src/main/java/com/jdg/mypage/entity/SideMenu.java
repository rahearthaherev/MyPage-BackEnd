package com.jdg.mypage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class SideMenu {
    @Id
    @Column
    private int index;
    @Column
    private String menu_key;
    @Column
    private String detail_key;
}
