
fun setUser(name: String, age: Int): String {
    return "Your name is $name, and you $age years old"
}

fun main () {

    val name = "Bagas Pangestu"
    val age  = 21

    val user = setUser(name, age)
    println(user)
}