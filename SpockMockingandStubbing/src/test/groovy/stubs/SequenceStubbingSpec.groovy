package stubs

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class SequenceStubbingSpec extends Specification{
    def"Inventory is always checked at the last possible moment"(){
        given: "a basket and TV"
        Product tv = new Product(name: "bravia",price: 1200,weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with fluctuating stock levels"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", _) >>> true >>false
        inventory.isEmpty() >>>false >> true
        basket.setWarehouseInventory(inventory)

        when:"user checks out the tv"
        basket.addProduct tv

        then:"order is to be shipped right away"
        basket.canShipCompletely()

        when:"user want another tv"
        basket.addProduct tv

        then:"order cannot be shipped"
        !basket.canShipCompletely()
    }
}
