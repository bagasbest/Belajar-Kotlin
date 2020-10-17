package app

import data.Function

fun main() {
    val function = Function("Bagas")
    //cara akses
    function.sayHello<String>("Joko")

    //cara akses
    function.sayHello(10)
    function.sayHello<Int>(10)

}