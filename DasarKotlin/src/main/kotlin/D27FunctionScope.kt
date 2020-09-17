
//buat function yang hanya bisa diakses dari function tertentu
fun satu () {
   //error
    // sayHello()
}

fun dua () {
    //error
    // sayHello()
}

fun main() {
    fun sayHello () {
        println("Hello World!")
    }

    sayHello()
}

