package com.jdg.mypage.repository;

import com.jdg.mypage.entity.MainProjectSkillList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MainProjectSkillRepository extends CrudRepository<MainProjectSkillList, Integer> {
    @Query("SELECT m.index FROM MainProjectSkillList m ORDER BY m.index DESC LIMIT 1")
    int getLastIndex();
}
