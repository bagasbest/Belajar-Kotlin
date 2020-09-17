
fun printHello (): Unit {
    println("Hello")
}

fun sum (a:Int, b:Int): Int {
    val total = a+b
    return total
}

fun bagi (a:Int, b:Int):Int {


    if(b == 0) {
        return 0
    } else {
        val total = a/b
        return  total
    }
}

fun main () {
    println(sum(10,10))

    val  res = sum(200,2020)
    println(res)

    val result = bagi(100,0)
    println(result)
}