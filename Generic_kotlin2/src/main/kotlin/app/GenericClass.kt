package app

import data.MyData

fun main() {
    val myData = MyData<String, Long>("Bagas", 120394232313)
    myData.printData()

    val myDataInt = MyData(12318892320, 12171972198)
    myDataInt.printData()

    myDataInt.printSecond()



    //multiple parameter type
    /*
        generic parameter dapat > 1, namun bedakan T nya
     */



}