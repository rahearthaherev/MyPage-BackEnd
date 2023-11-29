package com.jdg.mypage.repository;

import com.jdg.mypage.entity.StylingData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface StylingRepository extends CrudRepository<StylingData, Long> {
    @Query("SELECT s FROM StylingData s WHERE s.date = :date")
    StylingData findByDate(@Param("date")Timestamp date);
}
