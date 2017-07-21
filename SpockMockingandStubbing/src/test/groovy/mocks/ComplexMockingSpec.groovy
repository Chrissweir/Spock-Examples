package mocks

import com.spock.example.Product
import com.spock.example.mocks.BillableBasket
import com.spock.example.mocks.CreditCardProcessor
import com.spock.example.mocks.CreditCardResult
import com.spock.example.mocks.Customer
import com.spock.example.stubs.WarehouseInventory
import spock.lang.Specification

import javax.swing.CellRendererPane

class ComplexMockingSpec extends Specification{
    def "Card has no funds"(){
        given:"a basket, a customer and some products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350,weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and:"a credit card service"
        CreditCardProcessor creditCardService = Mock(CreditCardProcessor)
        basket.setCreditCardProcessor(creditCardService)

        and:"a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory){
            isProductAvailable(_,_) >>true
            isEmpty() >> false
        }
        basket.setWarehouseInventory(inventory)

        when:"user checks out two products"
        basket.addProduct(tv)
        basket.addProduct(camera)
        boolean charged = basket.fullCheckout(customer)

        then:"nothing is charged if credit card does not have enough money"
        1 * creditCardService.authorize(1550, customer) >> CreditCardResult.NOT_ENOUGH_FUNDS
        !charged
        0 * _
    }
}
