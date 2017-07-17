package com.spock.practices.Shared

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by cweir on 17/07/2017.
 */
class SharedSpec extends Specification{

    //Will be created only once
    @Shared
    CreditCardProcessor creditCardProcessor

    //Will be created multiple times
    BillableBasket basket

    def setupSpec() {
        //Expensive/slow initialization
        creditCardProcessor = new CreditCardProcessor()
    }

    def setup() {
        //Fast/cheap initialization
        basket = new BillableBasket()
        creditCardProcessor.newDayStarted()
        basket.setCreditCardProcessor(creditCardProcessor)
    }

    def "User buys a single product"() {
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)

        and: "user wants to buy the TV"
        basket.addProduct(tv)

        when: "use checks out"
        basket.checkout()

        then: "revenue is the same as the price of the TV"
        creditCardProcessor.currentRevenue == tv.price
    }

    def "User buys two products"() {
        given: "an empty basket and a camera"
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)

        and: "user wants to buy two cameras"
        basket.addProduct(camera,2)

        when: "use checks out"
        basket.checkout()

        then: "revenue is the same as the price of both products"
        creditCardProcessor.currentRevenue == 2 * camera.price
    }

    //Will run multiple times
    def cleanup(){
        basket.clearAllProducts()
    }

    //Will run only once
    def cleanupSpec(){
        creditCardProcessor.shutDown()
    }
}