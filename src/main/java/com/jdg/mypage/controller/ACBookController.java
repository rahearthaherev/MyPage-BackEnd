package com.jdg.mypage.controller;

import com.jdg.mypage.dto.ACHistoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/projects/acbook/")
@Slf4j
public class ACBookController {

    @PostMapping("write")
    public int writeHistory(@RequestBody ACHistoryDTO acHistoryDTO) {
        return 0;
    }
}
