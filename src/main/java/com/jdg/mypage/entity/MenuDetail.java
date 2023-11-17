package com.jdg.mypage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class MenuDetail {
    @Id
    @Column
    private String menu_sub_key;
    @Column
    private String detail_key;
    @Column
    private String menu_name;
    @Column
    private String menu_icon;
}
