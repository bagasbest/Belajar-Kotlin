package app

import data.ExecutiveCustomer
import data.PremiumCustomer

fun main() {
    val premiumCustomer = PremiumCustomer("Bagas")
    println(premiumCustomer.name)

    val executiveCustomer = ExecutiveCustomer("Eko", 1000000)
    println(executiveCustomer.name)
    println(executiveCustomer.balance)

}