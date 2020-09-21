package app

import data.Person

fun main() {
    val name = Person()
    name.firstName = "Bagas"
    name.middleName = ""
    name.lastName = "Pangestu"

    name.sayHello("Bagas")
    name.run()

    val  fullName = name.getFullName()
    println(fullName)
}