package com.jdg.mypage.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainProjectListDTO {
    private int index;
    private String projectId;
    private String projectName;
    private Timestamp startDate;
    private Timestamp endDate;
    private int teamNumber;
    private String startProcess;
    private String endProcess;
}
