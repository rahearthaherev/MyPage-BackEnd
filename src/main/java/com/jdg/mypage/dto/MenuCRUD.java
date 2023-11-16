package com.jdg.mypage.dto;


import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.entity.MenuType;
import com.jdg.mypage.entity.SideMenu;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MenuCRUD {
    private String updated;
    private String detail_id;
    private String id;

    public MenuType toMenuTypeEntity() {
        MenuType menuType = new MenuType();
        menuType.setMenu_type(updated);
        return menuType;
    }
    public SideMenu toSideMenuEntity() {
        SideMenu sideMenu = new SideMenu();
        sideMenu.setMenu_key(id);
        return sideMenu;
    }

    public MenuDetail toMenuDetailEntity() {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setDetail_key(detail_id);
        menuDetail.setMenu_icon("document");
        menuDetail.setMenu_name(updated);

        return menuDetail;
    }
}
