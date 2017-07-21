package mocks

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class ArgumentTypeVerificationSpec extends Specification{
    def "Warehouse is queried for each product"(){
        given: "a basket, a tv and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 12)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and:"a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "a user checks out both products"
        basket.addProduct(tv)
        basket.addProduct(camera)
        boolean readyToShip = basket.canShipCompletely()

        then:"order can be shipped"
        readyToShip
        2 * inventory.isProductAvailable(!null,1) >> true
    }
}
