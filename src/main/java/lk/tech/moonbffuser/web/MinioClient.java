package lk.tech.moonbffuser.web;

import lk.tech.moonbffuser.dto.minio.TileDto;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/v1")
public interface MinioClient {

    @PostExchange(value = "/tile", contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    TileDto uploadTile(@RequestPart("file") MultipartFile tile);

    @GetExchange("/tile")
    Resource getTile(@RequestParam("tileId") String tileId);

    @DeleteExchange("/tile")
    void deleteTile(@RequestParam("tileId") String tileId);

    @GetExchange("/atlas")
    Resource getAtlas(@RequestParam("atlasType") String atlasType);
}