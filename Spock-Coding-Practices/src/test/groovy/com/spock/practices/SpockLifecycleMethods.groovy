package com.spock.example

import spock.lang.Specification

/**
 * Created by cweir on 14/07/2017.
 */
class SpockLifecycleMethods extends  Specification{

    //This feature is for initialization of expensive objects eg. Database Connections
    def setupSpec(){
        println "Will run only once"
    }

    def setup(){
        println "Will run before EACH feature"
    }

    def "First feature being tested"(){
        expect: "trivial test"
        println "First feature runs"
        2 == 1 + 1
    }
    def "Second feature being tested"(){
        expect: "trivial test"
        println "Second feature runs"
        5 == 3 + 2
    }

    def cleanup(){
        println "Will run once after EACH feature"
    }

    def cleanupSpec(){
        println "Will run once at the end"
    }
}