package app

import data.Delegate
import data.MyBase

fun main() {
    val base = MyBase()
    base.sayHello("Bagas")

    val baseDelegate = Delegate(base)
    baseDelegate.sayHello("Joko")
    baseDelegate.sayGoodBye("Budi")

}