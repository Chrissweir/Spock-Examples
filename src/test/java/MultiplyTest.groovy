import spock.lang.Specification

/**
 * Created by cweir on 12/07/2017.
 */
class MultiplyTest extends Specification {
    def "Multiply two numbers to return the sum"() {
        when: "a new Multiply class is created"
        def multiply = new Multiply()

        then: "2 times 3 is 6"
        multiply.multiply(2, 3) == 6
    }

    def "Combine both multiplication and addition" (){
        when: "a new Multiply and Adder classes are created"
        def multiply = new Multiply();
        def adder = new Adder()

        then: "(2 plus 3) times 4 is 20"
        multiply.multiply(adder.add(2,3), 4) == 20
    }
}
