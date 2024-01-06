package com.jdg.mypage.repository;


import com.jdg.mypage.entity.MainProjectList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MainProjectListRepository extends CrudRepository<MainProjectList, String> {
    @Query("SELECT m FROM MainProjectList m ORDER BY m.startDate")
    Iterable<MainProjectList> getMainProjects();
}
