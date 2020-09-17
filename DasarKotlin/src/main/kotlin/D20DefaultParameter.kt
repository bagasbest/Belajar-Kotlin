
//default parameter
fun hello (fn:String, ln: String? = null) {

   if(ln == null) {
       println("Hello $fn")
   } else {
       println("Hello $fn $ln")
   }
}

fun main () {
    val fn:String = "Bagas"
    val ln:String = "Pangestu"

    hello(fn)
    hello(ln, fn)
}