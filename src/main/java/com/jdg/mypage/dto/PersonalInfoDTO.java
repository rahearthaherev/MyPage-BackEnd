package com.jdg.mypage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDTO {
    double height;
    String bodyType;
    String gender;
    String projectKey;
}
