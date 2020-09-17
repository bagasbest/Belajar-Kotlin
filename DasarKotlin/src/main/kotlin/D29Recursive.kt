
fun faktorial(n:Int):Int {
    return when (n) {
        1 -> 1
        else -> n*faktorial(n-1);
    }
}

fun main() {
    var n:Int = 5

    println(faktorial(5))
}