fun main() {
    fun hello (name:String, transformer: (String) -> String): String {
        val nameTransform = transformer(name)
        return "Hello $nameTransform"
    }

    val anonymousUpper = fun (value:String): String {
        if(value.isBlank()){
            return "Ups"
        } else {
            return value.toUpperCase()
        }
    }

    println(hello("Bagas", anonymousUpper))
    println(hello("", anonymousUpper))

    println(hello("", fun(value:String):String {
       return value.toLowerCase()
    }))
}