package com.amadou.gestionEtudiant.service.strategy.impl;

import com.amadou.gestionEtudiant.dto.SchoolDto;
import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidOperationException;
import com.amadou.gestionEtudiant.service.FlickrService;
import com.amadou.gestionEtudiant.service.SchoolService;
import com.amadou.gestionEtudiant.service.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("schoolStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveSchoolPhoto implements Strategy<SchoolDto> {

    private final FlickrService flickrService;
    private final SchoolService schoolService;

    @Override
    public SchoolDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {

        SchoolDto schoolDto = schoolService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException(
                    "Error saving school photo", ErrorCode.UPDATE_PHOTO_EXCEPTION
            );
        }
        schoolDto.setPhoto(urlPhoto);
        return schoolService.update(schoolDto, id);
    }
}
