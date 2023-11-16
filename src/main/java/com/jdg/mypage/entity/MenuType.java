package com.jdg.mypage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MenuType {
    @Id
    @Column
    private String detail_key;

    @Column
    private String menu_type;
}
