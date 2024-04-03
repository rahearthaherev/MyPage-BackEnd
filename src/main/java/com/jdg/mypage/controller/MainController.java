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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class MainController {
    private final MainRepository mainRepository;
    private final MainProjectSkillRepository mainProjectSkillRepository;
    private final MainProjectListRepository mainProjectListRepository;

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
    public ResponseEntity<Iterable<String>> getSkillStackType() {
        Iterable<String> typeList = mainRepository.getType();
        return new ResponseEntity<>(typeList, HttpStatus.OK);
    }

    @GetMapping("getskillstacklist")
    public ResponseEntity<Iterable<SkillStackDTO>> getSkillStackList() {
        Iterable<SkillStackList> skillStackList = mainRepository.findAll();
        Iterable<SkillStackDTO> skillStackDTOList = MainMapper.INSTANCE.iterableEntityToDto(skillStackList);
        return new ResponseEntity<>(skillStackDTOList, HttpStatus.OK);
    }

    @GetMapping("getprojectlist")
    public ResponseEntity<Iterable<MainProjectListDTO>> getMainProjectList() {
        Iterable<MainProjectList> mainProjectListIterable = mainProjectListRepository.getMainProjects();
        Iterable<MainProjectListDTO> mainProjectListDTOList = MainProjectMapper.INSTANCE.mainProjectListEntityToDto(mainProjectListIterable);
        return new ResponseEntity<>(mainProjectListDTOList, HttpStatus.OK);
    }

    @GetMapping("getprojectskilllist")
    public ResponseEntity<Iterable<MainProjectSkillDTO>> getMainProjectSkillList(){
        Iterable<MainProjectSkillList> mainProjectSkillLists = mainProjectSkillRepository.findAll();
        Iterable<MainProjectSkillDTO> mainProjectSkillDTOList = MainProjectMapper.INSTANCE.mainProjectSkillListEntityToDto(mainProjectSkillLists);
        return new ResponseEntity<>(mainProjectSkillDTOList, HttpStatus.OK);
    }

    @GetMapping("getskillstackname")
    public ResponseEntity<Iterable<String>> getSkillStackName() {
        Iterable<String> skillstackNameList = mainRepository.getName();
        return new ResponseEntity<>(skillstackNameList, HttpStatus.OK);
    }

    @GetMapping("getskillindex")
    public ResponseEntity<Integer> getSkillIndex() {
        int index = mainProjectSkillRepository.getLastIndex() + 1;
        return new ResponseEntity<>(index, HttpStatus.OK);

    }
    @PostMapping("addproject")
    public ResponseEntity<String> addProject(@RequestBody MainProjectList mainProjectList) {
        int index = mainProjectListRepository.getLastIndex() + 1;
        String projectId = makeProjectId(mainProjectList.getProjectName(), index);
        mainProjectList.setProjectId(projectId);
        mainProjectList.setIndex(index);
        log.info(mainProjectList.toString());
        mainProjectListRepository.save(mainProjectList);
        return new ResponseEntity<>(projectId, HttpStatus.OK);
    }

    @PostMapping("addskilllist")
    public ResponseEntity<Boolean> addSkillList(@RequestBody Iterable<MainProjectSkillList> mainProjectSkillLists) {
        mainProjectSkillRepository.saveAll(mainProjectSkillLists);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("modifyproject")
    public ResponseEntity<Boolean> modifyProject(@RequestBody MainProjectList mainProjectList){
        mainProjectSkillRepository.deleteProjectSkill(mainProjectList.getProjectId());
        mainProjectListRepository.save(mainProjectList);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("modifyskilllist")
    public ResponseEntity<Boolean> modifySkillList(@RequestBody Iterable<MainProjectSkillList> mainProjectSkillLists) {
        log.info(mainProjectSkillLists.toString());
        mainProjectSkillRepository.saveAll(mainProjectSkillLists);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("deleteproject")
    public ResponseEntity<Boolean> deleteProject(@RequestBody MainProjectList mainProjectList){
        mainProjectSkillRepository.deleteProjectSkill(mainProjectList.getProjectId());
        mainProjectListRepository.deleteById(mainProjectList.getProjectId());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public String makeProjectId(String projectName, int index) {
        return projectName.substring(0, 2) + String.format("%03d", index);

    }
}
