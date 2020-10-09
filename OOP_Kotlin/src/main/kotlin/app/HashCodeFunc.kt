package app

import data.Company

fun main() {
    val company1 = Company("Bagas")
    val company2 = Company("Bagas")

    //hachcode digunakan sebagai representasi number di kotlin
    // toString representasi string pada objek

    println(company1.hashCode() == company2.hashCode())
    println(company1.hashCode())
    println(company2.hashCode())
}