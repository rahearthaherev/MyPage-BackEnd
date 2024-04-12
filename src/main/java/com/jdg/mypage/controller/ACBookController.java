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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @PostMapping("save")
    public int writeSaveHistory(@RequestBody AcHistoryDTO acHistoryDTO) {
        String key = makeKey(acHistoryDTO.getDate(), acHistoryDTO.getTitle());
        AcHistory acHistory = convertHistoryEntity(acHistoryDTO, key);
        log.info(acHistory.toString());
        acBookHistoryRepository.save(acHistory);
        return 0;
    }

    @PostMapping("getyearhistory")
    public ResponseEntity<Iterable<AcHistoryDTO>> getYearHistory(@RequestBody Timestamp date) {
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

        int year = Integer.parseInt(sdfYear.format(date));

        Iterable<AcHistory> acHistory = acBookHistoryRepository.getYearHistory(year);
        Iterable<AcHistoryDTO> acHistoryDTOS = convertHistoryDTO(acHistory);
        log.info(acHistoryDTOS.toString());
        return new ResponseEntity<>(acHistoryDTOS, HttpStatus.OK);
    }

    @PostMapping("getmonthhistory")
    public ResponseEntity<Iterable<AcHistoryDTO>> getMonthHistory(@RequestBody Timestamp date) {
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");

        int year = Integer.parseInt(sdfYear.format(date));
        int month = Integer.parseInt(sdfMonth.format(date));

        Iterable<AcHistory> acHistory = acBookHistoryRepository.getMonthHistory(year, month);
        Iterable<AcHistoryDTO> acHistoryDTOS = convertHistoryDTO(acHistory);
        log.info(acHistoryDTOS.toString());
        return new ResponseEntity<>(acHistoryDTOS, HttpStatus.OK);
    }

    @PostMapping("getdatehistory")
    public ResponseEntity<Iterable<AcHistoryDTO>>  getDateHistory(@RequestBody Timestamp date) {
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");

        int year = Integer.parseInt(sdfYear.format(date));
        int month = Integer.parseInt(sdfMonth.format(date));
        int day = Integer.parseInt(sdfDay.format(date));

        Iterable<AcHistory> acHistory = acBookHistoryRepository.getDateHistory(year, month, day);
        Iterable<AcHistoryDTO> acHistoryDTOS = convertHistoryDTO(acHistory);
        log.info(acHistoryDTOS.toString());
        return new ResponseEntity<>(acHistoryDTOS, HttpStatus.OK);
    }

    @GetMapping("gettotal")
    public ResponseEntity<Integer> getTotal(){
        int account = assetsRepository.getAccount();
        int cash = assetsRepository.getCash();
        int credit = assetsRepository.getCredit();
        int result = account + cash - credit;
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }

    @GetMapping("getcredit")
    public ResponseEntity<Integer> getCredit(){
        Integer result = assetsRepository.getCredit();
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }

    @GetMapping("getcash")
    public ResponseEntity<Integer> getCash(){
        Integer result = assetsRepository.getCash();
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }

    @GetMapping("getaccount")
    public ResponseEntity<Integer> getAccount(){
        Integer result = assetsRepository.getAccount();
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
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
    private Iterable<AcHistoryDTO> convertHistoryDTO(Iterable<AcHistory> acHistories) {

        ArrayList<AcHistoryDTO> addedAcHistoryDTO = new ArrayList<>();

        for(AcHistory acHistory : acHistories){
            String key = acHistory.getKey();

            Iterable<AcDetail> acDetails = acBookDetailRepository.getDetails(key);
            Iterable<AcDetailsDTO> acDetailsDTOS = ACDetailMapper.INSTANCE.entityToDto(acDetails);

            AcDetailsDTO[] filteredDetails = filterByKey(acDetailsDTOS, key);

            Timestamp date = acHistory.getDate();
            String type = acHistory.getType();
            String payment = acHistory.getPayment();
            String account = acHistory.getAccount();
            String beforeAccount = acHistory.getBeforeAccount();
            String afterAccount = acHistory.getAfterAccount();
            int amount = acHistory.getAmount();
            String title = acHistory.getTitle();
            AcHistoryDTO acHistoryDTO = new AcHistoryDTO(key, date, type, payment, account, beforeAccount, afterAccount, amount, title, filteredDetails);

            addedAcHistoryDTO.add(acHistoryDTO);
        }

        return () -> Arrays.stream(addedAcHistoryDTO.toArray(new AcHistoryDTO[0])).iterator();
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

    private AcDetailsDTO[] filterByKey(Iterable<AcDetailsDTO> iterable, String key) {
        ArrayList<AcDetailsDTO> filteredDetails = new ArrayList<>();
        for (AcDetailsDTO details : iterable) {
            if (details.getKey().equals(key)) {
                filteredDetails.add(details);
            }
        }
        return filteredDetails.toArray(new AcDetailsDTO[0]);
    }
}
