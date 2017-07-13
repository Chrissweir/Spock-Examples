import spock.lang.Specification

public class Subtract{
    def minus(a,b) {
        return b - a
    }
}

class SubtractTest extends Specification {
    def "Subtract one number from another number to return the sum"() {
        when: "a new Subtract class is created"
        def subtract = new Subtract()

        then: "5 minus 3 is 2"
        subtract.minus(3, 5) == 2
    }
}