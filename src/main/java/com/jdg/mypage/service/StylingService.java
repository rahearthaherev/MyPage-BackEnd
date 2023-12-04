package com.jdg.mypage.service;

import com.jdg.mypage.dto.StylingDTO;
import com.jdg.mypage.dto.WeatherDTO;
import com.jdg.mypage.entity.StylingData;
import com.jdg.mypage.mapper.StylingMapper;
import com.jdg.mypage.repository.StylingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@EnableScheduling
public class StylingService {
    private final WebClient webClient;
    @Autowired
    StylingRepository stylingRepository;

    public StylingService() {
        this.webClient = WebClient.create();
    }

    public Mono<WeatherDTO> getDataFromApi(String URL) {
        return webClient.get().uri(URL).retrieve().bodyToMono(WeatherDTO.class);
    }

    @Scheduled(cron = "0 15 9  * * ?")
    public void getWeatherDataForStyling() {

        String URL = "https://api.pirateweather.net/forecast/tnZVRSTIILHMNbidLrb6uQzwEBiP0HX4/35.6894,139.692?exclude=alerts,minutely&units=si";
        Mono<WeatherDTO> weatherData = getDataFromApi(URL);
        WeatherDTO weatherDTO = weatherData.block();
        log.info(weatherDTO.toString());
        StylingDTO stylingDTO = weatherDTO.toStylingDTO();
        StylingData stylingData = StylingMapper.INSTANCE.dtoToEntity(stylingDTO);
        log.info(stylingData.toString());


        stylingRepository.save(stylingData);
    }

}
