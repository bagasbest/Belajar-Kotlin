package app

import data.Address

fun main() {
    //secondary constructor
    val address1 = Address("Jalan Lapas Raya", "Jati Agung")
    val address2 = Address("Jalan Margonda", "Bandung", "Indonesia")

    println(address1.street + ", " + address1.city)
    println(address2.street)
}