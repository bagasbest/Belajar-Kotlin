//variable constant
const val APP = "Belajar Kotlin"
const val VERSION = "0.0.1"

fun main () {

    //mutable variable, bisa dirubah -> var
    //immutable variable, tidak bisa diubah -> val

    val  name = "Bagas Pangestu"
    println(name);


    val age = 30L //long
    var firstName = "Bagas"
    println(age)
    firstName = name
    println(firstName)


    //nullable
    var nameYou:String? = null;
    nameYou = "Bagas"
    println(nameYou?.length);


    println("Nama Aplikasi $APP\nVersi $VERSION")
}