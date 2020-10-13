package app

import data.Gender

fun main() {
    val man = Gender.MALE
    val woman = Gender.FEMALE
    val allGender: Array<Gender> = Gender.values()

    val manFromString = Gender.valueOf("MALE")
    val womanFromString = Gender.valueOf("FEMALE")

    println(man)
    println(woman)
    println(allGender.toList())

    man.showDescription()
    woman.showDescription()

}