package com.jdg.mypage.controller;

import com.jdg.mypage.dto.MainProjectListDTO;
import com.jdg.mypage.dto.MainProjectSkillDTO;
import com.jdg.mypage.dto.SkillStackDTO;
import com.jdg.mypage.entity.MainProjectList;
import com.jdg.mypage.entity.MainProjectSkillList;
import com.jdg.mypage.entity.SkillStackList;
import com.jdg.mypage.mapper.MainMapper;
import com.jdg.mypage.mapper.MainProjectMapper;
import com.jdg.mypage.repository.MainProjectListRepository;
import com.jdg.mypage.repository.MainProjectSkillRepository;
import com.jdg.mypage.repository.MainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import org.springframework.http.HttpHeaders;

//3000번 포트에서 접속 허용
@CrossOrigin(originPatterns = "*")
//해당 클래스를 Controller로 인식
//RestController = Controller + ResponseBody
@RestController
//Request URL의 패턴으로 해당 클래스를 실행
@RequestMapping("/")
@Slf4j
public class MainController {
    private MainRepository mainRepository;
    private MainProjectSkillRepository mainProjectSkillRepository;
    private MainProjectListRepository mainProjectListRepository;

    public MainController(MainRepository mainRepository, MainProjectListRepository mainProjectListRepository, MainProjectSkillRepository mainProjectSkillRepository ) {
        this.mainRepository = mainRepository;
        this.mainProjectListRepository = mainProjectListRepository;
        this.mainProjectSkillRepository = mainProjectSkillRepository;
    }

    //Request URL의 말단 패턴으로 해당 메소드를 실행
    @GetMapping("")
    public String test() {
        return "Connection Successful";
    }

    @GetMapping("download")
    public ResponseEntity<FileSystemResource> downloadFile() {
        String filePath = "/home/ubuntu/mypage/スキルシート＿Jeong-DaeGyun.xlsx";

        File file = new File(filePath);
        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file_name.ext");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

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

    @GetMapping("getprojectlist")
    public Iterable<MainProjectListDTO> getMainProjectList() {
        Iterable<MainProjectList> mainProjectListIterable = mainProjectListRepository.getMainProjects();
        return MainProjectMapper.INSTANCE.mainProjectListEntityToDto(mainProjectListIterable);
    }

    @GetMapping("getprojectskilllist")
    public Iterable<MainProjectSkillDTO> getMainProjectSkillList(){
        Iterable<MainProjectSkillList> mainProjectSkillLists = mainProjectSkillRepository.findAll();
        return MainProjectMapper.INSTANCE.mainProjectSkillListEntityToDto(mainProjectSkillLists);
    }

    @GetMapping("getskillstackname")
    public Iterable<String> getSkillStackName() {
        return mainRepository.getName();
    }

    @GetMapping("getskillindex")
    public int getSkillIndex() {
        return mainProjectSkillRepository.getLastIndex() + 1;
    }
    @PostMapping("addproject")
    public String addProject(@RequestBody MainProjectList mainProjectList) {
        int index = mainProjectListRepository.getLastIndex() + 1;
        String projectId = makeProjectId(mainProjectList.getProjectName(), index);
        mainProjectList.setProjectId(projectId);
        mainProjectList.setIndex(index);
        log.info(mainProjectList.toString());
        mainProjectListRepository.save(mainProjectList);
        return projectId;
    }

    @PostMapping("addskilllist")
    public boolean addSkillList(@RequestBody Iterable<MainProjectSkillList> mainProjectSkillLists) {
        mainProjectSkillRepository.saveAll(mainProjectSkillLists);
        return true;
    }

    @PostMapping("modifyproject")
    public boolean modifyProject(@RequestBody MainProjectList mainProjectList){
        mainProjectSkillRepository.deleteProjectSkill(mainProjectList.getProjectId());
        mainProjectListRepository.save(mainProjectList);
        return true;
    }

    @PostMapping("modifyskilllist")
    public boolean modifySkillList(@RequestBody Iterable<MainProjectSkillList> mainProjectSkillLists) {
        log.info(mainProjectSkillLists.toString());
        mainProjectSkillRepository.saveAll(mainProjectSkillLists);
        return true;
    }

    @PostMapping("deleteproject")
    public boolean deleteProject(@RequestBody MainProjectList mainProjectList){
        mainProjectSkillRepository.deleteProjectSkill(mainProjectList.getProjectId());
        mainProjectListRepository.deleteById(mainProjectList.getProjectId());
        return true;
    }

    public String makeProjectId(String projectName, int index) {
        return projectName.substring(0, 2) + String.format("%03d", index);
    }
}
