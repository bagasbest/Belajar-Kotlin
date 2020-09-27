package app

import data.Smartphone

fun main() {
    val smartPhone = Smartphone("Samsung", "Android x")
    //print output
    println(smartPhone.name + ", " + smartPhone.os)
    //print any method hashcode
    println(smartPhone.hashCode())
    //print any method toString
    println(smartPhone.toString())
}