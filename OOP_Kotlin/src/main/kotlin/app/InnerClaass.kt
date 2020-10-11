package app

import data.Boss

fun main() {
    val boss = Boss("Bagas")
    val employee = boss.Employee("Joko")

    employee.hi()
}