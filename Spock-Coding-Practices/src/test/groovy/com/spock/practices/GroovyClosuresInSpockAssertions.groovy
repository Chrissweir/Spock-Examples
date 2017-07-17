package com.spock.practices

import org.spockframework.compiler.model.Spec
import spock.lang.Specification

/**
 * Created by cweir on 17/07/2017.
 */
class GroovyClosuresInSpockAssertions extends Specification{
    def "trivial test with Groovy closure"(){
        given:"A list of products"
        List<String> products = ["camera", "laptop", "hifi"]

        expect: "camera should be one of them"
        products.any{productName -> productName == "camera"}

        and:"hotdog is not one of them"
        products.every{productName -> productName != "hotdog"}
    }
}
