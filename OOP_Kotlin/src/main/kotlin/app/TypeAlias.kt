package app

import data.Application

typealias App = Application
typealias App2 = data2.Application
typealias Aplikasi = App

typealias StringSupplier = () -> String

fun sayHello (supplier : StringSupplier){
    println("Hello ${supplier()}")
}

fun main() {
    val app = App("Kotlin App")
    val aplikasi = Aplikasi("Kotlin")
    val app2 = App2("Kotlin v2")

    sayHello { "bagas" }
}