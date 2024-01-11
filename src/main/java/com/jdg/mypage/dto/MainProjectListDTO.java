package com.jdg.mypage.dto;

import lombok.*;

import java.sql.Date;
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
    private Date startDate;
    private Date endDate;
    private int teamNumber;
    private String startProcess;
    private String endProcess;
    private String description;
}
