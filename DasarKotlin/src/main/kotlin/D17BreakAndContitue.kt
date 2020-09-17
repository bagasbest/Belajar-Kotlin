
fun main () {

    //break -> stop perulangan
    //continue -> menghentikan perulangan saat ini

    for (i in 1..10) {
        if(i%2==0) {
            continue
        } else if(i == 9) {
            break
        }


        println("Perulangan ke-$i")
    }
    println("Perulangan selesai")

}