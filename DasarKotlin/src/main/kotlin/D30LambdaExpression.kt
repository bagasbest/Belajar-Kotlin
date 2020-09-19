
//to upper case
fun toUpper(value:String): String = value.toUpperCase()

//High-Order Function
fun hello(name: String, transformer: (String) -> String) : String {
    val nameTransform = transformer(name)
    return "Hello $nameTransform"
}

fun main() {
    val contohLambda: (String, String) -> String = {firstName: String, lastName: String ->
        val result = "$firstName $lastName"
        result
    }

    val result = contohLambda("Bagas", "Pangestu")
    println(result)

    //it
    val sayHello: (String) -> String = {
        "Hello $it"
    }
    println(sayHello("Bagas"))

    //function reference
    val toUpper : (String) -> String = ::toUpper
    println(toUpper("Bagas Pangestu"))

    //High-Order Function Call
    println(hello("Eko", {value: String -> value.toUpperCase()}))

    //Trailing Lambda
    val hasil1 = hello("Bagas") {
        value: String -> value.toLowerCase()
    }

    println(hasil1)

    val hasil2 = hello("Pangestu") {
        value: String -> value.toUpperCase()
    }
    println(hasil2)
}