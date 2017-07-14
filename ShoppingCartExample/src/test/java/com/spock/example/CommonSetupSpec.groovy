package com.spock.example

import spock.lang.Narrative
import spock.lang.Specification

/**
 * Created by cweir on 14/07/2017.
 */
@Narrative("This class outlines a different way of writing tests with the same setup and cleanup")
class CommonSetupSpec extends Specification{
    Product tv
    Product camera
    Basket basket

    def setup(){
        tv = new Product(name: "bravia",price: 1200,weight: 18)
        camera = new Product(name: "panasonic",price: 350,weight: 2)
        basket = new Basket()
    }

    def "A basket with one product has equal weight"(){
        when: "user wants to buy the TV"
        basket.addProduct(tv)

        then: "basket weight is equal to the TV"
        basket.currentWeight == tv.weight
    }

    def "A basket with two products weights as their sum (precondition)"() {
        expect: "that nothing should be inside"
        basket.currentWeight == 0
        basket.productTypesCount == 0

        when: "user want to buy the TV and the camera"
        basket.addProduct(tv)
        basket.addProduct(camera)

        then:"basket weight is equal to their sum"
        basket.currentWeight == (tv.weight + camera.weight)
    }

    def cleanup(){
        basket.clearAllProducts()
    }
}
