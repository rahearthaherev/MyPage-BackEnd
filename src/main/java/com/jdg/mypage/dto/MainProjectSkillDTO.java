package com.jdg.mypage.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainProjectSkillDTO {
    private String projectId;
    private String skillName;

    private int index;
}
