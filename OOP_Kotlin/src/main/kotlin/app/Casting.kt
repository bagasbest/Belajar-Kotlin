package app

import data.HandPhone
import data.Laptop
import data.Smartphone

fun printObject(any:Any) {
//    if(any is Laptop){
//        println("Laptop with name ${any.name}")
//    } else if (any is HandPhone) {
//        println("Phone with name ${any.name}")
//    } else{
//        println(any)
//    }

    when (any) {
        is Laptop -> println("Laptop with name ${any.name}")
        is HandPhone -> println("Smartphone with name ${any.name}")
        else -> println(any)

    }

}

fun printString(any: Any) {
    val value = any as String
    println(value)
}

fun printStringSave (any: Any) {
    val value = any as? String
    println(value)
}

fun main() {
    //kita dapat menerima parameter dalam bentuk apapun
    printObject("Bagas")
    printObject(123)
    printObject(1.1)
    printObject(Laptop("Acer"))
    printObject(HandPhone("Asus"))


    //printString("Bagas")
    //printString(1) error

    printStringSave("Bagas")
    printStringSave(1) //null hasilnya
}