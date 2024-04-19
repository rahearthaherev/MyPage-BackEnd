package com.jdg.mypage.repository;

import com.jdg.mypage.dto.AcDetailsDTO;
import com.jdg.mypage.entity.AcDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AcBookDetailRepository extends CrudRepository<AcDetail, String> {
    @Query("SELECT a FROM AcDetail a WHERE key = :key")
    Iterable<AcDetail> getDetails(@Param("key")String key);

    @Modifying
    @Query("DELETE FROM AcDetail a WHERE a.key = :key")
    int deleteDetails(@Param("key") String key);
}
