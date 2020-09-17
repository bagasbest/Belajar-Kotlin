fun main() {
    fun sayHello (name:String = ""):String {
//        if(name== "") {
//            return "Hello Bro!"
//        } else {
//            return "Hello $name"
//        }

        return if (name == "") {
            "Hello Bro"
        } else {
            "Hello $name"
        }
    }

    println(sayHello())
    println(sayHello("Bagas Pangestu"))

    fun sayWhen (name:String = ""):String {
//        when(name) {
//            "" -> return "Hello Bro!"
//            else -> return "Hello $name"
//        }

        return when (name) {
            "" -> "Hello Bro!"
            else -> "Hello $name"
        }
    }

    println(sayWhen())
    println(sayWhen("Bagas Pangestu"))

}