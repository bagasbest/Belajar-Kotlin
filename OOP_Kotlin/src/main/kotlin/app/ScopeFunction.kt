package app

import data.Student

fun main() {
    val student = Student("Bagas",16)

    val result = student.let {
//        println(it.name)
//        println(it.age)
        "Name ${it.name}, Age ${it.age} => it"
    }

    val result2 = student.run {
        "Name ${this.name}, Age ${this.age} =>this"
    }

    val result3 = student.apply {
        "Name ${this.name}, Age ${this.age} => apply"
    }

    val result4 = student.also {
        "Name ${it.name}, Age ${it.age} => also"
    }

    val result5 = with(student) {
        "Name ${this.name}, Age ${this.age}"
    }

    println(result)
    println(result2)
    println(result3)
    println(result4)
    println(result5)

}