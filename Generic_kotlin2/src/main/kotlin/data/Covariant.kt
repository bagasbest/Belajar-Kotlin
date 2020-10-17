package data

class Covariant<out T>(val data: T) {
    fun data() : T {
        return data
    }
}

fun main() {
    val covariantString = Covariant("Bagas")
    val covariantAny : Covariant<Any> = covariantString

    //output parameter data
    println(covariantString.data())
}