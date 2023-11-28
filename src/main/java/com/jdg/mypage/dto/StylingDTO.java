package com.jdg.mypage.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StylingDTO {
    private double maxTemperature;
    private double minTemperature;
    private double currentTemperature;
    private double windSpeed;
    private double humidity;
    private String icon;
    private double precipProbability;
}
