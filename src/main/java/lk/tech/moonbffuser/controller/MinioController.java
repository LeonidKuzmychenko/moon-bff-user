package lk.tech.moonbffuser.controller;

import lk.tech.moonbffuser.web.MinioClient;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MinioController {

    private final MinioClient minioClient;

    @GetMapping("/atlas")
    public ResponseEntity<Resource> getAtlas(@RequestParam("atlasType") String type) {
        Resource atlas = minioClient.getAtlas(type);
        return ResponseEntity.ok(atlas);
    }
}
