package app

fun displayLength(array : Array<*>){
    println("Total array is ${array.size}")
}

fun main () {
    val arrayInt: Array<Int> = arrayOf(1,3,2,5,6,4,87)
    val arrayString: Array<String> = arrayOf("Bagas", "sagab", "gasba")

    displayLength(arrayInt)
    displayLength(arrayString)
}