
fun main () {

//    val text = "Kotlin"
//    for (char in text) {
//        print("$char ")
//    }
//
//    val stat = "Kotlin is\"awesome\""y
//    print (stat)

//    val user  = setUser("Bagas", 21)
//    println(user)
//    printUser("Bagas")

//    var counter = 1
//    while (counter <= 7) {
//        println("Hello $counter")
//        counter++
//    }

//    val numberList = mutableListOf(1,2,3,4,5)
//    numberList.add(2)
//    numberList.removeAt(3)
//    numberList.add(1, 2);

    val setOfInteger = setOf(1,2,4,2,3,5,6,5,3,5,7,45,34,5,6)

    for(i in setOfInteger) {
        println(i);
    }


}

fun setUser (name: String, age: Int) = "Your name is $name, and your age: $age"

fun printUser (name: String) {
    println("your name is $name")
}