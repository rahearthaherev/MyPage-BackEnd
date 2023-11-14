package com.jdg.mypage.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//3000번 포트에서 접속 허용
@CrossOrigin(originPatterns = "http://localhost:3000")
//해당 클래스를 Controller로 인식
//RestController = Controller + ResponseBody
@RestController
//Request URL의 패턴으로 해당 클래스를 실행
@RequestMapping("/")
public class MainController {

    //Request URL의 말단 패턴으로 해당 메소드를 실행
    @GetMapping("")
    public String test() {
        return "Connection Successful";
    }
}
