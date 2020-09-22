package data

//super constructor
open class Customer (val name: String, val type: String, val balance:Long){
    constructor(name: String, type: String) : this (name, type, 0)
    constructor(name: String) : this (name, "Standard")
}

//call super constructor
class PremiumCustomer : Customer {
    constructor(name: String) : super(name, "Premium")
    constructor(name: String, balance: Long) : super(name, "Premium", balance)
}

class ExecutiveCustomer(name: String, balance: Long) : Customer(name, "Executive", balance) {
    constructor(name: String) : this(name, 0)
}