
fun main () {
    //when sama seperti switch-case

    val nilai:Char = 'd'
    val finalExam:Char = 'A'

    when(nilai){
        'A' -> {
            println("Mantappu")
        }
        'B' -> {
            println("Kerenn")
        }
        'C' -> {
            println("Oke lah")
        }
        'D' -> {
            println("Bahaya")
        } else -> {
            println("Tidak terindeks")
        }
    }

    //grouping
    when (nilai) {
        'A','B','C' -> {
            println("Oke lah")
        } 'D','E','F' -> {
            println("Bahaya")
        } else -> {
            println("Tidak terindeks")
        }
    }

    //in Expression
    val passValue: Array<Char?> = arrayOfNulls(3)
    passValue[0] = 'A'
    passValue[1] = 'B'
    passValue[2] = 'C'

    when (finalExam) {
        in passValue -> println("Selamat Anda Lulus");
        !in passValue -> println("Tidak terindeks")
    }

    //is Expression
    val name:String = "Bagas"
    when (name) {
        is String -> println("$name adalah String")
        !is String -> println("$name bukan string")
    }

    //when pengganti if else
    val examVal:Int = 70
    when {
        examVal > 80 -> println("Good Job")
        examVal >= 70 -> println("Lumayan")
        else -> println("Tidak terindeks")
    }
}