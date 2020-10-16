package data

open class Employee (val name: String){
    open fun sayHello(name: String) {
        println("Hello $name, My name is ${this.name}")
    }
}

class Manager (name: String) : Employee(name) {
    override fun sayHello (name:String) {
        println("Hello $name, mu name is Manager ${this.name}")
    }
}

class VicePrecident (name: String) : Employee(name) {
    override fun sayHello(name: String) {
        println("Hello $name, my name is VP ${this.name}")
    }
}