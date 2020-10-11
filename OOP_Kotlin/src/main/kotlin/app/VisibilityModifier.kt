package app

import data.SuperTeacher
import data.Teacher

fun main() {
    val teacher = SuperTeacher("Bagas")
    println(teacher.name)

    teacher.test();
}