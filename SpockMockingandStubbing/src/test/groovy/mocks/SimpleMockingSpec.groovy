package mocks

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.mocks.BillableBasket
import com.spock.example.mocks.CreditCardProcessor
import com.spock.example.mocks.Customer
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

    def "Credit card connection is always closed down"(){
        given:"a basket, a customer and a tv"
        Product tv = new Product(name:"bravia",price: 1200,weight: 18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John",vip: false,creditCard: "testCard")

        and:"a credit card service"
        CreditCardProcessor creditCardService = Mock(CreditCardProcessor)
        basket.setCreditCardProcessor(creditCardService)

        when:"a user checks out the tv"
        basket.addProduct tv
        basket.checkout(customer)

        then:"connection is always closed at the end"
        1 * creditCardService.shutdown()
    }
}