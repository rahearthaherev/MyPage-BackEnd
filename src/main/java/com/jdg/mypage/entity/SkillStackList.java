package com.jdg.mypage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SkillStackList {
    @Column
    private int index;
    @Column
    private String type;
    @Column
    private String name;
    @Id
    @Column
    private String id;
}
