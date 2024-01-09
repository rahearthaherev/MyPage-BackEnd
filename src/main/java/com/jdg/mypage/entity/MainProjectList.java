package com.jdg.mypage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MainProjectList {
    @Column
    private int index;
    @Column
    @Id
    private String projectId;
    @Column
    private String projectName;
    @Column
    private Timestamp startDate;
    @Column
    private Timestamp endDate;
    @Column
    private int teamNumber;
    @Column
    private String startProcess;
    @Column
    private String endProcess;
    @Column
    private String description;
}
