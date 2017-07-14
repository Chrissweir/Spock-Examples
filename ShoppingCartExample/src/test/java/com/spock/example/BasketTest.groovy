package com.spock.example

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

/**
 * Created by cweir on 14/07/2017.
 */
@Narrative(""" An empty basket starts with no weight. 
Adding products to the basket increases the weight. 
The weight is then used for billing during shipping calculations. 
Electronic goods have always zero weight.""")

@Title("Unit test for basket weight")
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

        //and: "it contains 2 products"
        //basket.productTypesCount == 2
    }

    def "An empty basket has no weight"(){
        expect: "zero weight when nothing is added"
        new Basket().currentWeight == 0
    }

    def "A basket with two products weights as their sum (precondition)"() {
        given: "an empty basket, a TV and a camera"
        Product tv = new Product(name: "bravia",price: 1200,weight: 18)
        Product camera = new Product(name: "panasonic",price: 350,weight: 2)
        Basket basket = new Basket()

        expect: "that nothing should be inside"
        basket.currentWeight == 0
        basket.productTypesCount == 0

        when: "user want to buy the TV and the camera"
        basket.addProduct(tv)
        basket.addProduct(camera)

        then:"basket weight is equal to their sum"
        basket.currentWeight == (tv.weight + camera.weight)
    }

    def "A basket with one product has the weight of that product"() {
        given: "an empty backet and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the TV"
        basket.addProduct(tv)

        then: "basket weight is equal to the TV"
        basket.currentWeight == tv.weight

        cleanup: "refresh basket resources"
        basket.clearAllProducts()
    }
}