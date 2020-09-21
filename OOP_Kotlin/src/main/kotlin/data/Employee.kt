package data

open class Employee (val name: String){
    fun sayHello(name: String) {
        println("Hello $name, My name is ${this.name}")
    }
}

class Manager (name: String) : Employee(name)

class VicePrecident (name: String) : Employee(name)