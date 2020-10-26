package app

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringLogReadWriteProperty(var data: String) :ReadWriteProperty<Any, String> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("Set property ${property.name} with value $data to $value")
        data = value
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("Get property ${property.name} to value $data")
        return data
    }

}

class Person (param: String) {
    var name: String by StringLogReadWriteProperty(param)
}

fun main() {
    val person = Person("Bagas")
    println(person.name)

    person.name = "Pangestu"
    println(person.name)
}