

fun main () {

    //perulangan dengan array
    val names: Array <String> = arrayOf("Bagas", "Iman", "Alfan")
    var total:Int = 0;
    for( name in names) {
        println(name)
        total++;
        println(total)
    }

    total = 0;
    //perulangan dengan range down to
    for(value in 100 downTo 0 step 5) {
        println(value)
        if(value == 0) {
            break;
        }
        total++;
        println(total)
    }

    //upTo + gabungan array
    val ukuranArray:Int = names.size - 1;
    for(i in 0..ukuranArray) {
        println("indeks $i = ${names.get(i)}")
    }
}