package stubs

import com.spock.example.Product
import com.spock.example.stubs.EnterprisyBasket
import com.spock.example.stubs.ServiceLocator
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class StubsInStubsSpec extends Specification{
    def "If warehouse is empty nothing can be shipped"(){
        given:"a tv"
        Product tv = new Product(name: "bravia",price: 1200,weight: 18)

        and:"an empty warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isEmpty() >> true
        ServiceLocator serviceLocator = Stub(ServiceLocator)
        serviceLocator.getWarehouseInventory() >> inventory

        and:"a basket"
        EnterprisyBasket basket = new EnterprisyBasket(serviceLocator)

        when:"user checks out the tv"
        basket.addProduct tv

        then:"order cannot be shipped"
        !basket.canShipCompletely()
    }
}