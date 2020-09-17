
//by default parameter bertipe data val
fun identitas (firstName:String, lastName:String?) {
    println("Hello $firstName $lastName")
}

fun main () {
    val fn:String = "Bagas"
    val ln:String = "Pangestu"
    identitas(fn, ln)
}