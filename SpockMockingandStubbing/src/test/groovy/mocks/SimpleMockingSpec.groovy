package mocks

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class SimpleMockingSpec extends Specification{
    def "If warehouse is empty nothing can be shipped"(){
        given:"a basket and a tv"
        Product tv = new Product(name:"bravia",price: 1200,weight: 18)
        Basket basket = new Basket()

        and:"and empty warehouse"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        inventory.isEmpty() >> true
        basket.setWarehouseInventory(inventory)

        when:"user checks out the tv"
        basket.addProduct tv

        then:"order cannot be shipped"
        !basket.canShipCompletely()
    }
}
