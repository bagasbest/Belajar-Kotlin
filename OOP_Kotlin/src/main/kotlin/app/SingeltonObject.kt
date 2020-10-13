package app

import data.Application
import data.Utilities

fun main() {
    Utilities.name = "Dirubah"
    println(Utilities.toUpper("Bagas"))
    a()
    b()

    println(Application.toUpper("Eko"))
}

fun a (){
    println(Utilities.name)
}

fun b () {
    println(Utilities.name)
}