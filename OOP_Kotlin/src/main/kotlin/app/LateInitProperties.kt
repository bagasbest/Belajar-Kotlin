package app

import data.Television

fun main() {
    val tv = Television()
    //set value sebelum di print
//    tv.initTelevision("Samsul")
    tv.brand = "Samsung"
    println(tv.brand)
}