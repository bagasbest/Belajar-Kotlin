package app

import data.Person

fun main() {
    val name = Person()
    name.firstName = "Bagas"

    name.sayHello("Bagas")
    name.sayHello("Bagas", "Pangestu")
}