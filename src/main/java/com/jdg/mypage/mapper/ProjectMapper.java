package com.jdg.mypage.mapper;

import com.jdg.mypage.dto.ProjectListDTO;
import com.jdg.mypage.entity.ProjectList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper{

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectList dtoToEntity(ProjectListDTO projectListDTO);
    ProjectListDTO entityToDto(ProjectList projectList);
}
