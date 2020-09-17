
fun hitungTotal(name:String, vararg values: Int):Int {
    var total = 0
    for(i in values) {
        total += i
    }
    return total
}

fun main() {
    val result = hitungTotal("Bagas", 10,10,20,30,40,50,60)
    println(result)

}