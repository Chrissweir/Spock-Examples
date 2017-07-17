package com.spock.practices.Shared

import spock.lang.Shared

/**
 * Created by cweir on 17/07/2017.
 */
class SharedSpec {

    @Shared
    CreditCardProcessor creditCardProcessor

    BillableBasket basket
}
