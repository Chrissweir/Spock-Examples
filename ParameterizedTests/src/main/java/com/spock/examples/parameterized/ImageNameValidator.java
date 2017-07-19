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

    public ImageExtensionCheck examineImageExtension(String fileName){
        //More enterprisy version of the previous method
        ImageExtensionCheck check = new ImageExtensionCheck();
        String lowerCase = fileName.toLowerCase(Locale.ENGLISH);
        boolean valid = lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png");
        check.setResult(valid);
        if(valid)
        {
        return check;
        }
        if(lowerCase.endsWith(".tiff"))
        {
        check.setErrorCode("ERROR002");
        check.setErrorDescription("Tiff files are not supported");
        }
        else
        {
        check.setErrorCode("ERROR999");
        check.setErrorDescription("Unsupported file type");
        }
        return check;
    }
}