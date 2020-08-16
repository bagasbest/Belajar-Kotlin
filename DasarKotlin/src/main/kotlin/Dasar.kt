fun main() {
    // output + new line
    println("Hello Bagas Pangestu Ganteng")

    //val vs var
    val name = "Bagas";
    println(name)
    println(if (true) "Always true" else "Always false")

    var name1 = "bagas";
    name1 = "bagas ganteng";
    println(name1)

    //concatenate
    val fn = "Bagas"
    val ln = " Pangestu"
    println(fn + ln)

    //sum
    val a= 10
    val b = 120
    println(a*b+a*b)

    //vocal increment
    var vocal ='A'
    println("Vocal: " + vocal++)
    println("Vocal: " + vocal++)
    println("Vocal: " + vocal++)
    println("Vocal: " + vocal--)
    println("Vocal: " + vocal--)

    val kotlin = "Kotlin"
    val first = kotlin[0]
    println("$first")

    for (c in kotlin){
        print("$c ")
    }
    println()


    val statement = "Kotlin is \"Awesome!\""


    val line = """
        Line 1
        Line 2
        Line 3
        Line 4
    """.trimIndent()

    print(line)

    //array campuran
    val mixArray = arrayOf(1, 3, 5, 7 , "Dicoding" , true)

    val intArray = intArrayOf(1,2,3,4,5)
    for (s in mixArray) {
        println(s)
    }



}