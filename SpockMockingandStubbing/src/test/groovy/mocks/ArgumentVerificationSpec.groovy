package mocks

import com.spock.example.Basket
import com.spock.example.Product
import com.spock.example.mocks.BillableBasket
import com.spock.example.mocks.CreditCardProcessor
import com.spock.example.mocks.Customer
import spock.lang.Specification

import javax.swing.CellRendererPane

class ArgumentVerificationSpec extends Specification{
    def "Vip status is correctly passed to credit card - simple"(){
        given: "a basket, a customer and a tv"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        CreditCardProcessor creditCardService = Mock(CreditCardProcessor)
        basket.setCreditCardProcessor(creditCardService)

        when:"a user checks out both products"
        basket.addProduct(tv)
        basket.addProduct(camera)
        basket.checkout(customer)

        then: "credit card is charged"
        1 * creditCardService.sale(1550, customer)
    }

    def "Vip status is correctly passed to credit card - vip"(){
        given:"a basket, a customer and a tv"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and:"a cred card service"
        CreditCardProcessor creditCardService = Mock(CreditCardProcessor)
        basket.setCreditCardProcessor(creditCardService)

        when:"a user checks out both products"
        basket.addProduct(tv)
        basket.addProduct(camera)
        basket.checkout(customer)

        then:"credit card is charged"
        1 * creditCardService.sale(1550, {client -> client.vip == false})
    }
}