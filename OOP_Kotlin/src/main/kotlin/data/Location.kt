package data

abstract class Location (val name: String)

//kita dapat mengggunakan abstract class sebagai parent
//semua kelas - kelas turunannya
class City(name:String) : Location(name)
class Country(name: String) : Location(name)