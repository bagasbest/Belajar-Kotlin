
fun main () {

//    val text = "Kotlin"
//    for (char in text) {
//        print("$char ")
//    }
//
//    val stat = "Kotlin is\"awesome\""
//    print (stat)

    val user  = setUser("Bagas", 21)
    println(user)
    printUser("Bagas")

}

fun setUser (name: String, age: Int) = "Your name is $name, and your age: $age"

fun printUser (name: String) {
    println("your name is $name")
}