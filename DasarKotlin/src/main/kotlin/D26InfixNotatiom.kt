
infix fun String.to(tyoe: String):String {
    if(tyoe == "UP"){
        return this.toUpperCase()
    } else {
        return this.toLowerCase()
    }
}

fun main() {
    val result = "Bagas Pangestu" to "UP"
    println(result)

    val res = "Bagas Pangestu" to "LOW"
    println(res)

    1000 downTo 1

}