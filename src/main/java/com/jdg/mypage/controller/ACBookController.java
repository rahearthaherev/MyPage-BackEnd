package com.jdg.mypage.controller;

import com.jdg.mypage.dto.AcDetailsDTO;
import com.jdg.mypage.dto.AcHistoryDTO;
import com.jdg.mypage.entity.AcDetail;
import com.jdg.mypage.entity.AcHistory;
import com.jdg.mypage.mapper.ACDetailMapper;
import com.jdg.mypage.mapper.ACHistoryMapper;
import com.jdg.mypage.repository.AcBookDetailRepository;
import com.jdg.mypage.repository.AcBookHistoryRepository;
import com.jdg.mypage.repository.AssetsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Arrays;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/projects/acbook/")
@Slf4j
@Transactional
public class ACBookController {
    private final AssetsRepository assetsRepository;
    private final AcBookHistoryRepository acBookHistoryRepository;
    private final AcBookDetailRepository acBookDetailRepository;

    public ACBookController(AssetsRepository assetsRepository, AcBookHistoryRepository acBookHistoryRepository, AcBookDetailRepository acBookDetailRepository) {
        this.assetsRepository = assetsRepository;
        this.acBookHistoryRepository = acBookHistoryRepository;
        this.acBookDetailRepository = acBookDetailRepository;
    }

    @PostMapping("write")
    public int writeHistory(@RequestBody AcHistoryDTO acHistoryDTO) {
        String key = makeKey(acHistoryDTO.getDate(), acHistoryDTO.getTitle());
        AcHistory acHistory = convertHistoryEntity(acHistoryDTO, key);
        Iterable<AcDetail> acDetail = convertDetailEntity(acHistoryDTO.getDetails(), key);
        log.info(acHistory.toString());
        log.info(acDetail.toString());
        acBookHistoryRepository.save(acHistory);
        acBookDetailRepository.saveAll(acDetail);

        return 0;
    }

    @PostMapping("getyearhistory")
    public AcHistoryDTO getYearHistory(@RequestBody Timestamp date) {
        log.info(date.toString());
        return null;
    }

    @PostMapping("getmonthhistory")
    public AcHistoryDTO getMonthHistory(@RequestBody Timestamp date) {
        log.info(date.toString());
        return null;
    }

    @PostMapping("getdatehistory")
    public AcHistoryDTO getDateHistory(@RequestBody Timestamp date) {
        log.info(date.toString());
        return null;
    }

    private AcHistory convertHistoryEntity(AcHistoryDTO acHistoryDTO, String key) {
        acHistoryDTO.setKey(key);
        if(acHistoryDTO.getType().equals("支出") || acHistoryDTO.getType().equals("輸入")) {
            acHistoryDTO.setAfterAccount(null);
            acHistoryDTO.setBeforeAccount(null);

            AcDetailsDTO[] acDetailsDTOS = acHistoryDTO.getDetails();
            int total = 0;
            for (AcDetailsDTO acDetailsDTO : acDetailsDTOS) {
                total = total + acDetailsDTO.getAmount();
            }
            acHistoryDTO.setAmount(total);
        }else {
            acHistoryDTO.setDetails(null);
            acHistoryDTO.setPayment(null);
            acHistoryDTO.setAccount(null);
        }

        return ACHistoryMapper.INSTANCE.dtoToEntity(acHistoryDTO);
    }

    private Iterable<AcDetail> convertDetailEntity(AcDetailsDTO[] acDetailsDTO, String key) {
        for(int i = 0; i < acDetailsDTO.length; i++) {
            acDetailsDTO[i].setKey(key);
            acDetailsDTO[i].setSubKey(key + String.format("%02d", i));
        }
        Iterable<AcDetailsDTO> acDetails = () -> Arrays.stream(acDetailsDTO).iterator();

        return ACDetailMapper.INSTANCE.dtoToEntity(acDetails);
    }

    private String makeKey(Timestamp date, String title) {
        return (title.charAt(0) + "" + title.charAt(1)).toUpperCase() + date.getTime();
    }
}
