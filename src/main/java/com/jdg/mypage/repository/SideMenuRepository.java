package com.jdg.mypage.repository;

import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.entity.SideMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SideMenuRepository extends CrudRepository<SideMenu, Long> {

}
