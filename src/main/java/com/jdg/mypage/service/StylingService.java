package com.jdg.mypage.service;

import com.jdg.mypage.dto.StylingDTO;
import com.jdg.mypage.dto.WeatherDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StylingService {
    private final WebClient webClient;

    public StylingService() {
        this.webClient = WebClient.create();
    }

    public Mono<WeatherDTO> getDataFromApi(String URL) {
        return webClient.get().uri(URL).retrieve().bodyToMono(WeatherDTO.class);
    }

    public StylingDTO getWeatherDataForStyling() {

        String URL = "https://api.pirateweather.net/forecast/tnZVRSTIILHMNbidLrb6uQzwEBiP0HX4/35.6894,139.692?exclude=alerts,minutely&units=si";
        Mono<WeatherDTO> weatherData = getDataFromApi(URL);
        WeatherDTO weatherDTO = weatherData.block();
        log.info(weatherDTO.toString());
        StylingDTO stylingDTO = weatherDTO.toStylingDTO();
        return stylingDTO;
    }
}
