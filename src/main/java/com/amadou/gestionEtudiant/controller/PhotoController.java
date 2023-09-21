package com.amadou.gestionEtudiant.controller;

import com.amadou.gestionEtudiant.service.strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.amadou.gestionEtudiant.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PhotoController {

    private final StrategyPhotoContext strategyPhotoContext;

    @PutMapping(value = APP_ROOT + "/photos/{context}/{id}/{title}", consumes = {"multipart/form-data"})
    public Object savePhoto(@PathVariable("context") String context, @PathVariable("id") Integer id, @RequestPart("file") MultipartFile photo, @PathVariable("title") String title) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title);
    }
}
