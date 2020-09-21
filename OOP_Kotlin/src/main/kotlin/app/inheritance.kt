package app

import data.Manager
import data.VicePrecident

fun main() {
    val manager = Manager("Bagas")
    manager.sayHello("Budi")

    val vicePrecident  = VicePrecident("Budi")
    vicePrecident.sayHello("Joko")
}