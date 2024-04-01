package com.jdg.mypage.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ACDetailsDTO {
    private String key;
    private String subKey;
    private String category;
    private String description;
    private int amount;
}
