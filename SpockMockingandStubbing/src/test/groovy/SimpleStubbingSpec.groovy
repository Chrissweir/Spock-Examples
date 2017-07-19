import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

class SimpleStubbingSpec extends Specification{
    def "If warehouse is empty nothing can be shipped"(){
        given:"a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and:"an empty warehouse"
        WarehouseInventory warehouseInventory = Stub(WarehouseInventory)
        warehouseInventory.isEmpty() >> true
        basket.setWarehouseInventory(warehouseInventory)

        when:"user checks out the tv"
        basket.addProduct(tv)

        then:"order cannot be shipped"
        !basket.canShipCompletely()
    }
}
