fun main () {

    val members: Array<String> = arrayOf("Bagas", "Iman", "Alfan")
    val value: Array<Byte> = arrayOf(1,2,3)
    val pendapatan: Array<Int> = arrayOf(20000000, 10000000, 22000000)

    println(members[0]) //sebelum diubah
    members.set(0, "Bagas Pangestu")
    println(members[0]) //setelah diubah

    //nullable array
    val member: Array<String?> = arrayOfNulls(10)
    member[0] = "Bagas"
    member[1] = "Pangestu"

    println(member[1])





}