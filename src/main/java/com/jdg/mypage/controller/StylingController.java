package com.jdg.mypage.controller;

import com.jdg.mypage.dto.StylingDTO;
import com.jdg.mypage.dto.WeatherDTO;
import com.jdg.mypage.entity.ClothesList;
import com.jdg.mypage.entity.ClothesType;
import com.jdg.mypage.repository.ClothesListRepository;
import com.jdg.mypage.repository.ClothesTypeRepository;
import com.jdg.mypage.service.StylingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@CrossOrigin(originPatterns = "http://localhost:3000")
@RequestMapping("/projects/styling/")
@Transactional
public class StylingController {
    @Autowired
    private ClothesTypeRepository clothesTypeRepository;
    @Autowired
    private ClothesListRepository clothesListRepository;
    @Autowired
    private StylingService stylingService;

    @GetMapping("gettype")
    public Iterable<ClothesType> getType() {
        Iterable<ClothesType> clothesTypes = clothesTypeRepository.findAll();
        log.info(clothesTypes.toString());
        return clothesTypes;
    }

    @GetMapping("getlist")
    public Iterable<ClothesList> getList() {
        Iterable<ClothesList> clothesLists = clothesListRepository.findAll();
        log.info(clothesLists.toString());
        return clothesLists;
    }

    @PostMapping("addcloth")
    public boolean addCloth(@RequestBody ClothesList clothesList) {
        log.info(clothesList.toString());
        if(clothesList.getIndex() == 0) {
            int index = clothesListRepository.GetNextIndex();
            clothesList.setIndex(index);
        }
        clothesList.setStatus(0);
        clothesListRepository.save(clothesList);
        return true;
    }
    @PostMapping("deletecloth")
    public boolean deleteCloth(@RequestBody ClothesList clothesList) {
        log.info(clothesList.toString());
        clothesListRepository.deleteCloth(clothesList.getName());
        return true;
    }

    @GetMapping("styling")
    public StylingDTO getWeather(){

        return stylingService.getWeatherDataForStyling();
    }
}
