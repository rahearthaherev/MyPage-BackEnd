package com.jdg.mypage.controller;

import com.jdg.mypage.entity.MenuCategory;
import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.entity.SideMenu;
import com.jdg.mypage.repository.MenuCategoryRepository;
import com.jdg.mypage.repository.MenuDetailRepository;
import com.jdg.mypage.repository.SideMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/sidemenu/")
public class MenuController {

    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @Autowired
    private SideMenuRepository sideMenuRepository;
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @GetMapping("")
    public Iterable<SideMenu> GetSideMenu() {
        Iterable<SideMenu> sideMenus = sideMenuRepository.findAll();
        return sideMenus;
    }

    @GetMapping("menuitem")
    public Iterable<MenuDetail> GetMenuItem() {
        Iterable<MenuDetail> menuDetails = menuDetailRepository.findAll();

        return menuDetails;
    }
    @GetMapping("menucategory")
    public  Iterable<MenuCategory> GetMenuCategory() {
        Iterable<MenuCategory> menuCategories = menuCategoryRepository.findAll();

        return menuCategories;
    }
}
