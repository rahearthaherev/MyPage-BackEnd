package com.jdg.mypage.dto;

import com.jdg.mypage.entity.StylingData;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class StylingDTO {
    private double maxTemperature;
    private double minTemperature;
    private double currentTemperature;
    private double windSpeed;
    private double humidity;
    private String icon;
    private double precipProbability;
    private Timestamp date;
}
