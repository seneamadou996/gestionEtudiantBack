package com.amadou.gestionEtudiant.service.strategy.impl;

import com.amadou.gestionEtudiant.dto.UserDto;
import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidOperationException;
import com.amadou.gestionEtudiant.service.FlickrService;
import com.amadou.gestionEtudiant.service.UserService;
import com.amadou.gestionEtudiant.service.strategy.Strategy;
import com.flickr4java.flickr.FlickrException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("userStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveUserPhoto implements Strategy<UserDto> {

    private final FlickrService flickrService;
    private final UserService userService;

    @Override
    public UserDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {

        UserDto userDto = userService.findUserById(id);
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException(
                    "Error saving school photo", ErrorCode.UPDATE_PHOTO_EXCEPTION
            );
        }
        userDto.setPhoto(urlPhoto);
        return userService.updateUser(userDto, id);
    }
}
