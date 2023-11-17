package com.jdg.mypage.service;

import com.jdg.mypage.dto.MenuCRUD;
import com.jdg.mypage.entity.MenuCategory;
import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.entity.MenuType;
import com.jdg.mypage.entity.SideMenu;
import com.jdg.mypage.repository.MenuDetailRepository;
import com.jdg.mypage.repository.MenuTypeRepository;
import com.jdg.mypage.repository.SideMenuRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Slf4j
public class MenuCRUDCategorize {
    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @Autowired
    private SideMenuRepository sideMenuRepository;
    @Autowired
    private MenuTypeRepository menuTypeRepository;

    private final String ADD = "Add";
    private final String UPDATE = "Update";
    private final String DELETE = "Delete";
    private final String CATEGORY = "category";
    private final String MENU = "menu";
    public void addMenu(MenuCRUD menuCRUD) {
        try {
            if (menuCRUD.getDetail_id() == null) {
                MenuType menuTypeEntity = menuCRUD.toMenuTypeEntity();
                int lastId = Integer.parseInt(menuTypeRepository.findLastId());
                String detail_key = String.format("%05d", ++lastId);
                menuTypeEntity.setDetail_key(detail_key);
                log.info(menuTypeEntity.toString());
                menuTypeRepository.save(menuTypeEntity);

                SideMenu sideMenuEntity = menuCRUD.toSideMenuEntity();
                sideMenuEntity.setDetail_key(detail_key);
                String menu_key = menuCRUD.getUpdated().substring(0, 2) + String.format("%03d", lastId);
                sideMenuEntity.setMenu_key(menu_key);
                log.info(sideMenuEntity.toString());
                sideMenuRepository.save(sideMenuEntity);

            } else {
                MenuDetail menuDetailEntity = menuCRUD.toMenuDetailEntity();
                String sub_key = menuCRUD.getUpdated().substring(0, 2) + String.format("%03d", menuDetailRepository.findNextIndex());
                menuDetailEntity.setMenu_sub_key(sub_key);
                log.info(menuDetailEntity.toString());
                menuDetailRepository.save(menuDetailEntity);
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
    public void updateMenu(MenuCRUD menuCRUD) {
        try{
            if(menuCRUD.getSelected().equals(CATEGORY)){
                MenuType menuTypeEntity = menuCRUD.toMenuTypeEntity();
                menuTypeRepository.save(menuTypeEntity);
                log.info(menuTypeEntity.toString());
            }else{
                MenuDetail menuDetailEntity = menuCRUD.toMenuDetailEntity();
                log.info(menuDetailEntity.toString());
                menuDetailRepository.save(menuDetailEntity);
            }
        }catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void deleteMenu(MenuCRUD menuCRUD) throws Exception {
        try{
            if(menuCRUD.getSelected().equals(CATEGORY)) {
                MenuType menuTypeEntity = menuCRUD.toMenuTypeEntity();
                SideMenu sideMenuEntity = menuCRUD.toSideMenuEntity();

                log.info(sideMenuEntity.toString());
                log.info(menuTypeEntity.toString());

                sideMenuRepository.delete(sideMenuEntity);
                menuTypeRepository.delete(menuTypeEntity);
            }else {
                MenuDetail menuDetailEntity = menuCRUD.toMenuDetailEntity();
                log.info(menuDetailEntity.toString());
                menuDetailRepository.delete(menuDetailEntity);
            }
        }catch(Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}
