package stubs

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class ExceptionStubbingSpec extends Specification{
    def"A problematic inventory means nothing can be shipped"(){
        given:"a basket and a tv"
        Product tv = new Product(name: "bravia",price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with serious issues"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", _) >> { throw new RuntimeException("critical error")}
        basket.setWarehouseInventory(inventory)

        when:"user checks out the tv"
        basket.addProduct tv

        then:"order cannot be shipped"
        !basket.canShipCompletely()
    }
}
