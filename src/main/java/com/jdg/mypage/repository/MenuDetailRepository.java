package com.jdg.mypage.repository;

import com.jdg.mypage.entity.MenuDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuDetailRepository extends CrudRepository<MenuDetail, Long> {
    @Query("SELECT m FROM MenuDetail m ORDER BY m.menu_sub_key")
    Iterable<MenuDetail> findAllOrderByKey();

    @Query("SELECT nextval('menu_detail_sequence')")
    int findNextIndex();

    @Query("SELECT e FROM MenuDetail e WHERE e.detail_key = (SELECT d.detail_key FROM MenuDetail d WHERE d.menu_sub_key = :key)")
    List<MenuDetail> findSameCatagoryMenu(@Param("key") String key);


}