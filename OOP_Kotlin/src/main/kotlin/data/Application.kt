package data

class Application(val name: String) {
    object Utilities {
        fun toUpper(value : String): String {
            return value.toUpperCase()
        }
    }
}