package com.jdg.mypage.repository;

import com.jdg.mypage.entity.MenuDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MenuDetailRepository extends CrudRepository<MenuDetail, Long> {
@Query("SELECT nextval('menu_detail_sequence')")
    int findNextIndex();
}
