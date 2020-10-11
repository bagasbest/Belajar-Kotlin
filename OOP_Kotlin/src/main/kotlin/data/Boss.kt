package data

class Boss(val bossName: String) {

   inner class Employee (val name: String){
        fun hi(){
            println("Hi, my name is $name, my Boss is ${this@Boss.bossName}")
        }
    }
}