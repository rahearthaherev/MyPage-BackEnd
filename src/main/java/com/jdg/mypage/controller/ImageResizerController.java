package com.jdg.mypage.controller;

import com.jdg.mypage.dto.ImageResizingDTO;
import com.jdg.mypage.service.ImageResizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@CrossOrigin(originPatterns = "*")
@RestController
@Slf4j
@RequestMapping("/projects")
@Transactional
public class ImageResizerController {
    ImageResizeService imageResizeService;
    public ImageResizerController(ImageResizeService imageResizeService) {
        this.imageResizeService = imageResizeService;
    }
    @PostMapping("/imageresizing")
    public String imageResizing(@RequestBody ImageResizingDTO imageResizingDTO) {
        log.info(imageResizingDTO.toString());
        String resizedImage = imageResizeService.resizeImage(imageResizingDTO);
        return resizedImage;
    }
}
