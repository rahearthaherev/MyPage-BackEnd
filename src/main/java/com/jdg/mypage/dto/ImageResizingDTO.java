package com.jdg.mypage.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageResizingDTO {
    private String image;
    private int oldWidth;
    private int oldHeight;
    private int newWidth;
    private int newHeight;
    private String oldExtention;
    private String newExtention;
    private String fileName;
    private String type;
}
