package com.jdg.mypage.repository;

import com.jdg.mypage.entity.ClothesList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClothesListRepository extends CrudRepository<ClothesList, Long> {
    @Query("SELECT nextval('cloth_list_sequence')")
    int GetNextIndex();
}
