package com.jdg.mypage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectListDTO {
    String projectName;
    String projectKey;
}
