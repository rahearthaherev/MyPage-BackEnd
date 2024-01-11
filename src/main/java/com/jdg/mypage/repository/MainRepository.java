package com.jdg.mypage.repository;

import com.jdg.mypage.entity.SkillStackList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MainRepository extends CrudRepository<SkillStackList, String> {
    @Query("SELECT DISTINCT s.type FROM SkillStackList s")
    public Iterable<String> getType();

    @Query("SELECT DISTINCT s.name FROM SkillStackList s")
    public Iterable<String> getName();
}
