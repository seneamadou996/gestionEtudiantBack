package com.amadou.gestionEtudiant.service.strategy.impl;

import com.amadou.gestionEtudiant.dto.EtudiantDto;
import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidOperationException;
import com.amadou.gestionEtudiant.service.EtudiantService;
import com.amadou.gestionEtudiant.service.FlickrService;
import com.amadou.gestionEtudiant.service.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("etudiantStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveEtudiantPhoto implements Strategy<EtudiantDto> {

    private final FlickrService flickrService;
    private final EtudiantService etudiantService;


    @Override
    public EtudiantDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        EtudiantDto etudiantDto = etudiantService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException(
                    "Error saving student photo", ErrorCode.UPDATE_PHOTO_EXCEPTION
            );
        }
        etudiantDto.setPhoto(urlPhoto);
        return etudiantService.update(etudiantDto, id);
    }

}
