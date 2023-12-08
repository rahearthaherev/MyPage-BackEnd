package com.jdg.mypage.repository;

import com.jdg.mypage.entity.ClothesList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClothesListRepository extends CrudRepository<ClothesList, Long> {
    @Query("SELECT nextval('cloth_list_sequence')")
    int GetNextIndex();
    @Modifying
    @Query("DELETE FROM ClothesList c WHERE c.name = :name")
    int deleteCloth(@Param("name")String name);

    @Modifying
    @Query("UPDATE ClothesList c SET c.status = :status  WHERE c.name=:name")
    int updateClothStatus(@Param("name") String name, @Param("status") int status);
}
