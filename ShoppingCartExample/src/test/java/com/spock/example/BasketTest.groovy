package com.spock.example

import spock.lang.Specification

/**
 * Created by cweir on 14/07/2017.
 */
class BasketTest extends Specification {
    def "A basket with one product has equal weight"(){
        given: "an empty backet and TV"
        Product tv = new Product(name:"bravia", price:1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the TV"
        basket.addProduct(tv)

        then: "basket weight is equal to the TV"
        basket.currentWeight == tv.weight
    }

    def "A basket with more than one product has a weight equal to their sum"() {
        given: "an empty basket, a TV and a camera"
        Product tv = new Product(name: "bravia",price: 1200,weight: 18)
        Product camera = new Product(name: "panasonic",price: 350,weight: 2)
        Basket basket = new Basket()

        when: "user want to buy the TV and the camera"
        basket.addProduct(tv)
        basket.addProduct(camera)

        then:"basket weight is equal to their sum"
        basket.currentWeight == (tv.weight + camera.weight)
    }
}
