import com.spock.examples.grouping.DefaultCustomerResolver
import com.spock.examples.grouping.EnterprisyBasket
import com.spock.examples.grouping.Product
import com.spock.examples.grouping.WarehouseInventory
import spock.lang.Specification

/**
 * Created by cweir on 17/07/2017.
 */
class InventorySpec extends Specification{

    def "Buying products reduces the inventory availability"() {
        given: "an inventory with products"
        Product laptop = new Product(name: "toshiba", price: 1200, weight: 5)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        WarehouseInventory warehouseInventory = new WarehouseInventory()
        warehouseInventory.preload(laptop, 3)
        warehouseInventory.preload(camera, 5)
        warehouseInventory.preload(hifi, 2)

        and: "an empty basket"
        EnterprisyBasket basket = new EnterprisyBasket()
        basket.setWarehouseInventory(warehouseInventory)
        basket.setCustomerResolver(new DefaultCustomerResolver())
        basket.setLanguage("english")
        basket.setNumberOfCaches(3)
        basket.enableAutoRefresh()

        when:"user gets a laptop and two cameras"
        basket.addProduct(camera,2)
        basket.addProduct(laptop)

        and: "user completes the transaction"
        basket.checkout()

        then: "warehouse is updated accordingly"
        !warehouseInventory.isEmpty()
        warehouseInventory.getBoxesMovedToday() == 3
        warehouseInventory.availableOfProduct("toshiba") == 2
        warehouseInventory.availableOfProduct("panasonic") == 3
        warehouseInventory.availableOfProduct("jvc") == 2
    }
}
