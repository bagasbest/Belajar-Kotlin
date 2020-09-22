package app

import data.*

fun main() {
    val manager = Manager("Bagas")
    manager.sayHello("Budi")

    val vicePrecident  = VicePrecident("Budi")
    vicePrecident.sayHello("Joko")

    val name = Player("Bagas")
    name.sayHello("Mega")

    val coach = Coach ("Axel")
    coach.sayHello("Bagas")

    val team = Team ("Bagas")
    team.sayHello("UHU")
}