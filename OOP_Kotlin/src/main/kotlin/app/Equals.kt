package app

import data.Company

fun main() {
    val company1 = Company("Bagas")
    val company2 = Company("Bagas")

    //default objek Jika dilakukan komparasi
    println(company1 == company2)
    println(company1 ==company1)
    println(company2 == company2)

    //kita dapat merubahnya dengan cara override fungsi equals
}