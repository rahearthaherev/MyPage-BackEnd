package com.jdg.mypage.repository;

import com.jdg.mypage.entity.MainProjectSkillList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MainProjectSkillRepository extends CrudRepository<MainProjectSkillList, Integer> {
    @Query("SELECT m.index FROM MainProjectSkillList m ORDER BY m.index DESC LIMIT 1")
    int getLastIndex();

    @Modifying
    @Transactional
    @Query("DELETE FROM MainProjectSkillList m WHERE m.projectId = :projectId")
    int deleteProjectSkill(@Param("projectId") String projectId);
}
