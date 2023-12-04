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
public class StylingPersonalInfo {
    @Column
    double height;
    @Column
    String bodyType;
    @Column
    String gender;
    @Column
    @Id
    String projectKey;
}
