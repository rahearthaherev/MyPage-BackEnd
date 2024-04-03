package com.jdg.mypage.repository;

import com.jdg.mypage.entity.AcHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AcBookHistoryRepository extends CrudRepository<AcHistory, String> {
    @Query("SELECT a FROM AcHistory a WHERE EXTRACT(YEAR FROM a.date) = :year")
    Iterable<AcHistory> getYearHistory(@Param("year") String year);

    @Query("SELECT a FROM AcHistory a WHERE EXTRACT(YEAR FROM a.date) = :year AND EXTRACT(MONTH FROM a.date) = :month")
    Iterable<AcHistory> getMonthHistory(@Param("year") String year, @Param("month") String month);

    @Query("SELECT a FROM AcHistory a WHERE a.date = :date")
    Iterable<AcHistory> getDateHistory(@Param("date") String date);
}
