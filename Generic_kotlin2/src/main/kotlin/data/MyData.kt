package data

class MyData <T,U> (val firstData: T, val secondData: U) {

    //getneric parameter type gunakan T = type = generic datanya
    fun getData() : T = firstData

    fun getSecond() : U = secondData

    fun printData() {
        println("Data is $firstData $secondData")
    }

    fun printSecond(){
        println("Data is $secondData")
    }

}