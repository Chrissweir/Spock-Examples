package stubs

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

import javax.rmi.CORBA.Stub

class ArgumentStubbingSpec extends Specification{
    def "If warehouse does not have all products, order cannot be shipped"(){
        given: "an empty basket, a TV and a camera"
        Product tv = new Product(name: "bravia",price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350,weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with partial availability"
        //Creating a Spock Stub
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia",1) >> true
        inventory.isProductAvailable("panasonic", 1) >> false
        inventory.isEmpty() >> false
        basket.setWarehouseInventory(inventory)

        when:"user checks out both products"
        basket.addProduct tv
        basket.addProduct camera

        then: "order cannot be shipped right away"
        !basket.canShipCompletely()
    }

    def "If warehouse has the product on stock everything is fine"(){
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia",price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with enough stock"
        //Creating a Spock Stub
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia",1) >> true
        inventory.isEmpty() >> false
        basket.setWarehouseInventory(inventory)

        when:"user checks out the TV"
        basket.addProduct tv

        then: "order can be shipped right away"
        basket.canShipCompletely()
    }
    def "If warehouse has both products everything is fine"() {
        given: "a basket, a TV and a camera"
        Product tv = new Product(name:"bravia",price:1200,weight:18)
        Product camera = new Product(name:"panasonic",price:350,weight:2)
        Basket basket = new Basket()

        and:"a warehouse with enough stock"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable( _, 1) >> true
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv and the camera"
        basket.addProduct tv
        basket.addProduct camera

        then: "order can be shipped right away"
        basket.canShipCompletely()
    }
}