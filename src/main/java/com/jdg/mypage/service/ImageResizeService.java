package com.jdg.mypage.service;

import com.jdg.mypage.dto.ImageResizingDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

@Service
@ToString
@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class ImageResizeService {
    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }

    // 이미지 윗부분 삭제하여 크기 조절 메서드
    private BufferedImage cropImage(BufferedImage originalImage,int oldWidth, int oldHeight, int newWidth, int newHeight, String type) {
        BufferedImage croppedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g = croppedImage.createGraphics();
        switch (type) {
            case "Top" :
                g.drawImage(originalImage, 0, 0, oldWidth, newHeight, 0, (oldHeight - newHeight), oldWidth, oldHeight, null);
                break;
            case "Bottom" :
                g.drawImage(originalImage, 0, 0, oldWidth, newHeight, 0, 0, oldWidth, newHeight, null);
                break;
            case "Left" :
                g.drawImage(originalImage, 0, 0, newWidth, oldHeight, (oldWidth - newWidth), 0, oldWidth, oldHeight, null);
                break;
            case "Right" :
                g.drawImage(originalImage, 0, 0, newWidth, oldHeight, 0, 0, newWidth, oldHeight, null);
                break;
            default:
                break;
        }
        g.dispose();
        return croppedImage;
    }

    private String encodeImageToBase64(BufferedImage resizedImage) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Base64를 BufferedImage로 디코딩하는 메서드
    private BufferedImage decodeBase64ToImage(String base64Image) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            return ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 이미지 크기 조절 및 Base64 인코딩 메서드
    public String resizeImage(ImageResizingDTO imageResizingDTO) {
        BufferedImage originalImage = decodeBase64ToImage(imageResizingDTO.getImage());
        if (originalImage == null) {
            return null;
        }
        BufferedImage resizedImage = null;
        if(imageResizingDTO.getType().equals("Fixed")){
            resizedImage = resizeImage(originalImage, imageResizingDTO.getNewWidth(), imageResizingDTO.getNewHeight());
        }else {
            resizedImage = cropImage(originalImage, imageResizingDTO.getOldWidth(), imageResizingDTO.getOldHeight(), imageResizingDTO.getNewWidth(), imageResizingDTO.getNewHeight(), imageResizingDTO.getType());
        }
        return encodeImageToBase64(resizedImage);
    }
}
