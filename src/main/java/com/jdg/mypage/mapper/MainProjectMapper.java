package com.jdg.mypage.mapper;

import com.jdg.mypage.dto.MainProjectListDTO;
import com.jdg.mypage.dto.MainProjectSkillDTO;
import com.jdg.mypage.entity.MainProjectList;
import com.jdg.mypage.entity.MainProjectSkillList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MainProjectMapper {
    MainProjectMapper INSTANCE = Mappers.getMapper(MainProjectMapper.class);

    Iterable<MainProjectList> mainProjectListDtoToEntity(Iterable<MainProjectListDTO> mainProjectListDTO);
    Iterable<MainProjectListDTO> mainProjectListEntityToDto(Iterable<MainProjectList> mainProjectList);

    Iterable<MainProjectSkillList> mainProjectSkillListDtoToEntity(Iterable<MainProjectSkillDTO> mainProjectSkillDTO);
    Iterable<MainProjectSkillDTO> mainProjectSkillListEntityToDto(Iterable<MainProjectSkillList> mainProjectSkillList);
}
