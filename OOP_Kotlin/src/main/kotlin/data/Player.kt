package data

//paren class
open class Player (val name:String) {
    //parent function
    open fun sayHello(name: String) {
        println("Hello $name, my name is ${this.name}")
    }
}

//inheritance class
open class Coach(name: String) : Player(name) {
    //override from parent function
    final override fun sayHello(name: String) {
        println("Hello $name,this is Coach ${this.name} ")
    }
}

class SuperCoach(name:String) : Coach(name) {
//    override fun sayHello(name: String) {
//        println("Hello $name, my name is super coach ${this.name}")
//    }
}

class Team (name: String) : Player(name) {
    override fun sayHello(name: String) {
        println("Hello ${this.name}, this is $name Esport")
    }
}