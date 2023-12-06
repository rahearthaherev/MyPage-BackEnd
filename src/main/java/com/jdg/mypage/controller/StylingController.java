package com.jdg.mypage.controller;

import com.jdg.mypage.dto.PersonalInfoDTO;
import com.jdg.mypage.dto.StylingDTO;
import com.jdg.mypage.dto.WeatherDTO;
import com.jdg.mypage.entity.*;
import com.jdg.mypage.mapper.ProjectMapper;
import com.jdg.mypage.mapper.StylingMapper;
import com.jdg.mypage.repository.*;
import com.jdg.mypage.service.StylingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(originPatterns = "*")
@RequestMapping("/projects/styling/")
@Transactional
@AllArgsConstructor
public class StylingController {

    private final ClothesTypeRepository clothesTypeRepository;

    private final ClothesListRepository clothesListRepository;

    private final StylingRepository stylingRepository;

    private final StylingPersonalInfoRepository stylingPersonalInfoRepository;

    private final ProjectListRepository projectListRepository;


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

    @GetMapping("weatherinfo")
    public StylingDTO getWeather(){
        //日付取得
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        Timestamp timestamp = Timestamp.valueOf(startOfDay);

        StylingData stylingData = stylingRepository.findByDate(timestamp);
        StylingDTO stylingDTO = StylingMapper.INSTANCE.entityToDTO(stylingData);
        log.info(stylingDTO.toString());
        return stylingDTO;
    }

    @GetMapping("getpersonalinfo")
    public PersonalInfoDTO getPersonalInfo() {
        final String projectKey = getProectKey("Today's Styling");
        if(projectKey == null) {
            return null;
        }

        Optional<StylingPersonalInfo> optionalStylingPersonalInfo = stylingPersonalInfoRepository.findById(projectKey);
        if(!optionalStylingPersonalInfo.isPresent()){
            log.info("Personal Data is not defined");
            return null;

        }
        StylingPersonalInfo stylingPersonalInfo = optionalStylingPersonalInfo.get();
        log.info(stylingPersonalInfo.toString());
        return StylingMapper.PersonalInfoMapper.INSTANCE.entityToDTO(stylingPersonalInfo);
    }

    @PostMapping("updatepersonalinfo")
    public boolean updatePersonalInfo(@RequestBody PersonalInfoDTO personalInfoDTO) {
        log.info(personalInfoDTO.toString());
        final String projectKey = getProectKey("Today's Styling");
        if(projectKey == null) {
            return false;
        }
        StylingPersonalInfo stylingPersonalInfo =  StylingMapper.PersonalInfoMapper.INSTANCE.dtoToEntity(personalInfoDTO);
        stylingPersonalInfo.setProjectKey(projectKey);

        stylingPersonalInfoRepository.save(stylingPersonalInfo);

        return true;
    }

    private String getProectKey(String name) {
        Optional<ProjectList> optionalProjectList = projectListRepository.findById(name);
        if(!optionalProjectList.isPresent()){
            log.info("Project key is not defined");
            return null;
        }
        ProjectList projectList = optionalProjectList.get();
        log.info(projectList.toString());
        return ProjectMapper.INSTANCE.entityToDto(projectList).getProjectKey();
    }
}
