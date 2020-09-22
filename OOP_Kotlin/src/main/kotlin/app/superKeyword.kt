package app

import data.Rectangle

fun main() {
    val superKeyword = Rectangle ()
    println("Corner ${superKeyword.corner}")
    println("Parent Corner ${superKeyword.parentCorner}")

    superKeyword.printName()
}