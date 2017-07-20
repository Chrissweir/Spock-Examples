package stubs

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.ShippingCalculator
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class DynamicStubbingSpec extends Specification{
    def "Basket handles shipping charges according to product count"(){
        given:"a basket and several products"
        Product tv = new Product(name: "bravia", price: 1200 ,weight: 18)
        Product camera = new Product(name: "panasonic", price: 350 ,weight: 2)
        Product hifi = new Product(name: "jvc", price: 600 ,weight: 5)
        Product laptop = new Product(name: "toshiba", price: 800 ,weight: 10)
        Basket basket = new Basket()

        and:"a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_,_) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator)
        shippingCalculator.findShippingCostFor(_,_) >> {Product product, int count -> count * 10}
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        basket.addProduct tv,2
        basket.addProduct camera,2
        basket.addProduct hifi
        basket.addProduct laptop,3

        then: "cost is correctly calculated"
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + 3 * laptop.price +basket.getProductCount() * 10
    }

    def "Downloadable goods do not have shipping cost"(){
        given:"a basket and several products"
        Product tv = new Product(name: "bravia", price: 1200 ,weight: 18)
        Product camera = new Product(name: "panasonic", price: 350 ,weight: 2)
        Product hifi = new Product(name: "jvc", price: 600 ,weight: 5)
        Product laptop = new Product(name: "toshiba", price: 800 ,weight: 10)
        Product ebook = new Product(name: "learning exposure", price: 30 ,weight: 0)
        Product suite = new Product(name: "adobe essentials", price: 200 ,weight: 0)
        Basket basket = new Basket()

        and:"a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_,_) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each physical product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator)
        shippingCalculator.findShippingCostFor(_,_) >> {Product product, int count -> product.weight==0 ? 0 : 10 * count}
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        basket.addProduct tv,2
        basket.addProduct camera,2
        basket.addProduct hifi
        basket.addProduct laptop
        basket.addProduct ebook
        basket.addProduct suite, 3

        then: "cost is correctly calculated"
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + laptop.price + ebook.price + 3*suite.price +60
    }
}