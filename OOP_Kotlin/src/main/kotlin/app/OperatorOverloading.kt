package app

import data.Fruit

fun main() {
    val fruit = Fruit(100)
    val fruit2 = Fruit(100)
    val fruit3 = fruit + fruit2
    println(fruit3)
    println(fruit - fruit2)
}