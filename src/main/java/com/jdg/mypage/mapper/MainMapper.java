package com.jdg.mypage.mapper;


import com.jdg.mypage.dto.SkillStackDTO;
import com.jdg.mypage.entity.SkillStackList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MainMapper {
    MainMapper INSTANCE = Mappers.getMapper(MainMapper.class);

    SkillStackList dtoToEntity(SkillStackDTO skillStackDTO);
    SkillStackDTO entityToDto(SkillStackList skillStackList);

    Iterable<SkillStackDTO> iterableEntityToDto(Iterable<SkillStackList> skillStackList);
}
