package app

import data.Car

fun main() {

    //constructor call
    val toyota = Car("Toyota")
    val almaz = Car("Wuling", 2019)

    println(toyota.brand)
    println(toyota.year)
    println(almaz.brand + " " + almaz.year)
}