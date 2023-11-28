package com.jdg.mypage.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class WeatherDTO {
    private String timezone;
    private double offset;
    private Currently currently;
    private Hourly hourly;
    private Daily daily;

    public StylingDTO toStylingDTO() {
        StylingDTO stylingDTO = new StylingDTO();
        stylingDTO.setHumidity(getHumidity());
        stylingDTO.setIcon(getIcon());
        stylingDTO.setPrecipProbability(getIntensity());
        stylingDTO.setWindSpeed(getWindSpeed());
        stylingDTO.setCurrentTemperature(getTemperature());
        stylingDTO.setMaxTemperature(getMaxTemperature());
        stylingDTO.setMinTemperature(getMinTemperature());

        return stylingDTO;
    }

    public double getMaxTemperature() {
        return daily.getData().get(0).getTemperatureHigh();
    }

    public double getMinTemperature() {
        return daily.getData().get(0).getTemperatureLow();
    }

    public double getTemperature() {
        return currently.getTemperature();
    }

    public double getWindSpeed() {
        return daily.getData().get(0).getWindSpeed();
    }

    public double getHumidity() {
        return daily.getData().get(0).getHumidity();
    }

    public double getIntensity() {
        return daily.getData().get(0).getPrecipIntensity();
    }

    public String getIcon() {
        return daily.getData().get(0).getIcon();
    }
}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Currently {
    private long time;
    private String summary;
    private String icon;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double cloudCover;
}
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Hourly {
    private String summary;
    private String icon;
    private List<Data> data;
}
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Daily {
    private String summary;
    private String icon;
    private List<Data> data;
}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Data {
    private long time;
    private String summary;
    private String icon;
    private double temperature;
    private double temperatureLow;
    private double temperatureHigh;
    private double humidity;
    private double windSpeed;
    private double cloudCover;
    private double precipIntensity;
}