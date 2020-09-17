fun String.hello(): String = "Hello $this"

fun String.printHello():Unit = println("Hello $this")


fun main () {
    val name = "Bagas"
    println(name.hello())

    //name.printHello()
    "Bagas".printHello()
}