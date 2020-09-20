package app

import data.Person

fun main() {
    val bagas = Person()
    bagas.firstName = "Bagas"

    val iman = Person()
    iman.firstName = "Iman"

    val alfan = Person()
    alfan.firstName = "Alfan"


    println(bagas.firstName)
    println(iman.firstName)
    println(alfan.firstName)
}