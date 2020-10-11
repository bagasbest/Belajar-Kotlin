package app

import data.Minus
import data.Modulo
import data.Operation
import data.Plus
import kotlin.math.abs

fun operation(value1: Int, value2: Int, operation: Operation) : Int {
    return when(operation){
        is Plus -> value1 + value2
        is Minus -> abs(value1 - value2)
        is Modulo -> value1 % value2
    }
}

fun main() {
    println(operation(10, 10, Plus()))
    println(operation(10, 15, Minus()))
    println(operation(10, 7, Modulo()))
}