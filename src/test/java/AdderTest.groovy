import spock.lang.Specification

/**
 * Created by cweir on 12/07/2017.
 */
class AdderTest extends Specification {
    def "Adding two numbers to return the sum"() {
        when: "a new Adder class is created"
        def adder = new Adder();

        then: "1 plus 1 is 2"
        adder.add(1,1) == 2
    }

    def "Order of numbers does not matter"(){
        when: "a new Adder class is created"
        def adder = new Adder();

        then: "2 plus 3 is 5"
        adder.add(2, 3) == 5

        and: "3 plus 2 is 5"
        adder.add(3,2) == 5
    }
    def "Multiple classes used"(){
        when: "a new Adder and Multiply and SubtractTest classes are created"
        def multiply = new Multiply();
        def adder = new Adder();
        def subtract = new Subtract()

        then: "(2 plus 3) times 4 minus 5 is 15"
        subtract.minus(5,multiply.multiply(adder.add(2,3), 4)) == 15
    }
}
