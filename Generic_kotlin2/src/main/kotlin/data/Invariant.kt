package data

class Invariant <T> (val data: T)

fun main() {
    val invariantString = Invariant("String")
    //sifat generic type adalah invariant yang berarti objek tidak dapat di subtitude ke child atau superclassnya
//    val invariantAny: Invariant<Any> = invariantString

}