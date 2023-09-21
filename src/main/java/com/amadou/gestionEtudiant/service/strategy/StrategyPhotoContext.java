package com.amadou.gestionEtudiant.service.strategy;

import com.amadou.gestionEtudiant.exception.ErrorCode;
import com.amadou.gestionEtudiant.exception.InvalidOperationException;
import com.amadou.gestionEtudiant.service.strategy.impl.SaveEtudiantPhoto;
import com.amadou.gestionEtudiant.service.strategy.impl.SaveSchoolPhoto;
import com.amadou.gestionEtudiant.service.strategy.impl.SaveUserPhoto;
import com.flickr4java.flickr.FlickrException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class StrategyPhotoContext {

    private final BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determineContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private void determineContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "school":
                strategy = beanFactory.getBean(beanName, SaveSchoolPhoto.class);
                break;
            case "etudiant":
                strategy = beanFactory.getBean(beanName, SaveEtudiantPhoto.class);
                break;
            case "user":
                strategy = beanFactory.getBean(beanName, SaveUserPhoto.class);
                break;
                default: throw new InvalidOperationException(
                        "Unknown context for saving the photo", ErrorCode.UNKNOWN_CONTEXT);
        }
    }


}
