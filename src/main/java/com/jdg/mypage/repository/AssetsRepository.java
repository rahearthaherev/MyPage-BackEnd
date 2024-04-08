package com.jdg.mypage.repository;

import com.jdg.mypage.entity.AcAssets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AssetsRepository extends CrudRepository<AcAssets, String> {
    @Query("SELECT SUM(a.amount) FROM AcAssets a WHERE a.type = '通常'")
    Integer getAccount();

    @Query("SELECT SUM(a.amount) FROM AcAssets a WHERE a.type = '現金'")
    Integer getCash();

    @Query("SELECT SUM(a.amount) FROM AcAssets a WHERE a.type = 'クレジット'")
    Integer getCredit();

    @Query("SELECT SUM(a.amount) FROM AcAssets a WHERE a.type = '貯金'")
    Integer getSave();

}
