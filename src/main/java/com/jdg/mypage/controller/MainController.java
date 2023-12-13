package com.jdg.mypage.controller;

import com.jdg.mypage.dto.SkillStackDTO;
import com.jdg.mypage.entity.SkillStackList;
import com.jdg.mypage.mapper.MainMapper;
import com.jdg.mypage.repository.MainRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import org.springframework.http.HttpHeaders;

//3000번 포트에서 접속 허용
@CrossOrigin(originPatterns = "*")
//해당 클래스를 Controller로 인식
//RestController = Controller + ResponseBody
@RestController
//Request URL의 패턴으로 해당 클래스를 실행
@RequestMapping("/")
public class MainController {
    private MainRepository mainRepository;

    public MainController(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    //Request URL의 말단 패턴으로 해당 메소드를 실행
    @GetMapping("")
    public String test() {
        return "Connection Successful";
    }

    @GetMapping("download")
    public ResponseEntity<FileSystemResource> downloadFile() {
        // 다운로드할 파일 경로
        String filePath = "/home/ubuntu/mypage/スキルシート＿Jeong-DaeGyun.xlsx";

        // 파일을 FileSystemResource로 래핑하여 다운로드 응답 생성
        File file = new File(filePath);
        FileSystemResource resource = new FileSystemResource(file);

        // 파일 다운로드를 위한 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file_name.ext");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // 파일 다운로드 응답 생성 및 반환
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(resource);
    }

    @GetMapping("getskillstacktype")
    public Iterable<String> getSkillStackType() {
        Iterable<String> typeList = mainRepository.getType();
        return typeList;
    }

    @GetMapping("getskillstacklist")
    public Iterable<SkillStackDTO> getSkillStackList() {
        Iterable<SkillStackList> skillStackList = mainRepository.findAll();
        return MainMapper.INSTANCE.iterableEntityToDto(skillStackList);
    }
}
