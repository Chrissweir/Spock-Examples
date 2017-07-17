package com.spock.examples.parameterized;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by cweir on 17/07/2017.
 */
public class ImageNameValidator {

    public boolean isValidImageExtension(String fileName){
        String lowerCase = fileName.toLowerCase(Locale.ENGLISH);
        return lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png");
    }
}
