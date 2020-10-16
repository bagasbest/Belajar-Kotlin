package app

import data.Employee
import data.Manager
import data.VicePrecident

fun main() {
    var employee = Employee("Bagas")
    employee.sayHello("Budi")

    employee = Manager("Joko")
    employee.sayHello("Budi")

    employee = VicePrecident("Andi")
    employee.sayHello("Budi")

}