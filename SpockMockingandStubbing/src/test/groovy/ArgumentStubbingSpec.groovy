import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

import javax.rmi.CORBA.Stub

class ArgumentStubbingSpec extends Specification{
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
}
