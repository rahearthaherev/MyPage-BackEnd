package com.jdg.mypage.repository;

import com.jdg.mypage.entity.BoardList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends CrudRepository<BoardList, Long> {
    @Query("SELECT e FROM BoardList e WHERE e.menu_sub_key = :key AND e.title LIKE CONCAT('%', :search, '%') ORDER BY e.create_time DESC ")
    Iterable<BoardList> getBoardList(@Param("key") String key, @Param("search") String search);

    @Query("SELECT e FROM BoardList e WHERE e.board_key = :key")
    BoardList getBoard(@Param("key") String key);

    @Query("SELECT nextval('board_list_sequence')")
    int findCurrentIndex();
}
