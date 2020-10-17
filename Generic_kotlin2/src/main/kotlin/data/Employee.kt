package data

interface CanSayHello {
    fun sayHello(name: String)
}

open class Employee

class Manager : Employee()

class VicePresident : Employee(), CanSayHello {
    override fun sayHello(name: String) {
        println("Hello $name, I'm Vice President")
    }
}

//default Any kalo tidak menyebutkan kelas
class Programmer<T : Employee>(val employee: T)

class Company<T>(val employee: T) where T : Employee, T : CanSayHello {
    fun sayHello(name: String) {
        println("Hello $name")
    }
}


fun main() {
    val data1 = Programmer(Employee())
    val data2 = Programmer(Manager())
    val data3 = Programmer(VicePresident())
//    val data4 = Programmer("String")

//    val data5 = Company(Employee()) //error, karena tidak dapat melakukan CanSayHello
//    val data6 = Company(Manager()) //error
    val data7 = Company(VicePresident())
//    val data8 = Company("String") // error

    data7.sayHello("Bagas")
}