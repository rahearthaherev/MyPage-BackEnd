package com.jdg.mypage.controller;

import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.repository.MenuDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/board/")
@Transactional
public class BoardController {
    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @PostMapping("header")
    public List<MenuDetail> GetMenuList(@RequestBody MenuDetail menu) {
        log.info(menu.toString());
        List<MenuDetail> menuList = menuDetailRepository.findSameCatagoryMenu(menu.getMenu_sub_key());
        log.info(menuList.toString());
        return menuList;
    }
}
