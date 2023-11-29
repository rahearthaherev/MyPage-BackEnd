package com.jdg.mypage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StylingData {
    @Column
    private double maxTemperature;
    @Column
    private double minTemperature;
    @Column
    private double currentTemperature;
    @Column
    private double windSpeed;
    @Column
    private double humidity;
    @Column
    private String icon;
    @Column
    private double precipProbability;
    @Id
    @Column
    private Timestamp date;
}
