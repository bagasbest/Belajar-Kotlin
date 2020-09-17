

/*
    Kadang ada function yang parameternya banyak sekali
    Hal ini tentu menyulitkan kita saat akan memanggail funtion tersebut, kita perlu mengingat urutan parameter
    Untungnya kotlin pmemiliki fitur yang namanya Named Argument
    Named Argument adalah fitur dimana kita dapat menyebutkan nama parameter saat memanggiil function
    dengan demikian kita tidak wajib tahu posisi tiap parameter

 */

fun fullName (
        firstName: String,
        middleName: String,
        lastName: String
) {
    println("Hello $firstName $middleName $lastName")
}

fun main () {
    fullName("Bagas", "Iman", "Alfan")

    fullName(
            lastName = "Bagas",
            middleName = "Alfan",
            firstName = "Iman"
    )
}