package com.spock.examples.parameterized

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by cweir on 17/07/2017.
 */
class ImageNameValidatorTest extends Specification {

    def setupSpec(){
        println "Setup prepares first run"
    }
    //This is an example of duplication of unit test i.e. bad practice
   /* def "Valid images are JPG"(){
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
    */

    def "Valid images are PNG and JPEG files"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictureFile) == validPicture

        where: "sample image names are"
        pictureFile       || validPicture
        "scenery.jpg"     || true
        "house.jpeg"      || true
        "car.png"         || true
        "sky.tiff"        || false
        "dance_bunny.gif" || false
    }

    def "Valid images are PNG and JPEG files (enterprise version)"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        when: "an image is checked"
        ImageExtensionCheck imageExtensionCheck = validator.examineImageExtension(pictureFile)

        then: "expect that only valid filenames are accepted"
        imageExtensionCheck.result == validPicture
        imageExtensionCheck.errorCode == error
        imageExtensionCheck.errorDescription == description

        where: "sample image names are"
        pictureFile       || validPicture | error      | description
        "scenery.jpg"     || true         | ""         | ""
        "house.jpeg"      || true         | ""         | ""
        "car.png"         || true         | ""         | ""
        "sky.tiff"        || false        | "ERROR002" | "Tiff files are not supported"
        "dance_bunny.gif" || false        | "ERROR999" | "Unsupported file type"
    }

    //Example of Data Pipes
    def "Valid images are PNG and JPEG files only"(){
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictureFile) == validPicture

        where:"sample image names are"
        pictureFile << ["scenery.jpg","house.jpeg","car.png","sky.tiff","dance_bunny.gif"]
        validPicture << [true,true,true,false,false]
    }
}