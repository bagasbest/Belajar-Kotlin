package app

import data.Student
import data.sayHello
import data.upperName

fun main() {
    val student = Student("Bagas", 21)
    student.sayHello("Budi")
    println(student.upperName)
}