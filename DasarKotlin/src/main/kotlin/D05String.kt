
fun main () {

    //string template
    var firstName:String = "Bagas"
    var lastName:String = "Pangestu"

    println("$firstName $lastName")

    println("total $firstName $lastName char ${firstName.length + lastName.length} \n")

    //trim margin

    var address1: String = """
        |Jalan Ahmad Akuan,
        |Gang Arrahim,
        |Nomor 135,
        |Rejosari,
        |Kotabumi
        |Lampung Utara
        """.trimMargin()

    println(address1)
}