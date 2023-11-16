package com.jdg.mypage.repository;

import com.jdg.mypage.entity.MenuType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MenuTypeRepository extends CrudRepository<MenuType, Long> {
    @Query("SELECT m.detail_key FROM MenuType m ORDER BY m.detail_key DESC LIMIT 1")
    String findLastId();
}
