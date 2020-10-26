package app

class Container<T> (var data: T)

fun copyContainer(from: Container<out Any>, to:Container<in Any>){
    to.data = from.data
}

fun main() {
    //invaariant dipaksa menjadi covariant atau contravariant
    val data1 = Container("Data 1")
    val data2 : Container<Any> = Container("Data 2")
    copyContainer(data1, data2)

    println(data1.data)
    println(data2.data)
}