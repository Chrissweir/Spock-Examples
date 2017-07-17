package com.spock.examples.parameterized

import spock.lang.Specification

/**
 * Created by cweir on 17/07/2017.
 */
class ImageNameValidatorTest extends Specification {
    def "Valid images are JPG"(){
        given:"an image extension checker and a jpg file"
        ImageNameValidator validator = new ImageNameValidator()
        String pictureFile = "scenery.jpg"

        expect: "that the filename is valid"
        validator.isValidImageExtension(pictureFile)
    }

    def "Valid images are JPEG"(){
        given:"an image extension checker and a jpeg file"
        ImageNameValidator validator = new ImageNameValidator()
        String pictureFile = "house.jpeg"

        expect: "that the filename is valid"
        validator.isValidImageExtension(pictureFile)
    }

    def "Tiff are invalid"(){
        given:"an image extension checker and a tiff file"
        ImageNameValidator validator = new ImageNameValidator()
        String pictureFile = "sky.tiff"

        expect: "that the filename is valid"
        !validator.isValidImageExtension(pictureFile)
    }
}
