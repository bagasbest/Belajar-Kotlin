package data

//primary constructor
class Car(paramBrand: String, paramName: String, paramYear: Int = 2020) {
    init {
        //blok akan dijalankan ketika objek dibuat
        //jangan terlalu berat kode programnya
        println("Car $paramBrand dibuat")
    }

    constructor(paramBrand: String, paramName: String) :
            //secondary constructor wajib memanggil primary constructor
            this(paramBrand, paramName, 2020) {
        println("Secondary Costructor")
    }

    //secondary constructor dapat lebih dari 1
    constructor(paramBrand: String) :
            this(paramBrand, "") {
        println("Secondary constructor 2")
    }

    var brand: String = paramBrand
    var name: String = paramName
    var year: Int = paramYear
}


