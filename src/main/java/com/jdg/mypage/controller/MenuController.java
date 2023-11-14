package com.jdg.mypage.controller;

import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.entity.SideMenu;
import com.jdg.mypage.repository.MenuDetailRepository;
import com.jdg.mypage.repository.SideMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/sidemenu/")
public class MenuController {

    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @Autowired
    private SideMenuRepository sideMenuRepository;

    @GetMapping("")
    public Iterable<SideMenu> GetSideMenu() {
        Iterable<SideMenu> sideMenuEntity = sideMenuRepository.findAll();
        return sideMenuEntity;
    }

    @GetMapping("menuitem")
    public Iterable<MenuDetail> GetMenuItem() {
        Iterable<MenuDetail> menuDetailEntity = menuDetailRepository.findAll();

        return menuDetailEntity;
    }

}
