package com.jdg.mypage.controller;

import com.jdg.mypage.dto.MenuCRUD;
import com.jdg.mypage.entity.MenuCategory;
import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.entity.SideMenu;
import com.jdg.mypage.service.MenuCRUDCategorize;
import com.jdg.mypage.repository.MenuCategoryRepository;
import com.jdg.mypage.repository.MenuDetailRepository;
import com.jdg.mypage.repository.SideMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/sidemenu/")
@Slf4j
public class MenuController {

    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @Autowired
    private SideMenuRepository sideMenuRepository;
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;
    @Autowired
    private MenuCRUDCategorize menuCRUDCategorize;

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

    @PostMapping("menuadd")
    public String MenuAdd(@RequestBody MenuCRUD menuCRUD) {
        try {
            log.info(menuCRUD.toString());
            menuCRUDCategorize.addMenu(menuCRUD);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("menuupdate")
    public String MenuUpdate(@RequestBody MenuCRUD menuCRUD) {
        try {
            log.info(menuCRUD.toString());
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("menudelete")
    public String MenuDelete(@RequestBody MenuCRUD menuCRUD) {
        try {
            log.info(menuCRUD.toString());
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
