package mocks

import com.spock.example.Product
import com.spock.example.mocks.BillableBasket
import com.spock.example.mocks.CreditCardProcessor
import com.spock.example.mocks.Customer
import spock.lang.Specification

class OrderMockingSpec extends Specification{
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

        //Order of invocations in then block does not matter to Spock
        then: "credit card is charged and CC service is closed down"
        1 * creditCardService.sale(1200,customer)
        1 * creditCardService.shutdown()
    }
    def "Credit card connection is closed down in the end"(){
        given: "a basket, a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        BillableBasket basket = new BillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and:"a credit card service"
        CreditCardProcessor creditCardService = Mock(CreditCardProcessor)
        basket.setCreditCardProcessor(creditCardService)

        when:"a user checks out the tv"
        basket.addProduct tv
        basket.checkout()

        then:"credit card is charged and"
        1 * creditCardService.sale(_,_)

        then:"the credit card service is closed down"
        1 * creditCardService.shutdown()
    }
}
