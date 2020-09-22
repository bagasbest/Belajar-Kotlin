package data

open class Shape {
    open val corner: Int = -1
    open fun printName() {
        println("This is shape")
    }
}

class Rectangle : Triangle () {
    override val corner: Int = 4
    val parentCorner: Int = super.corner
    override fun printName() {
        println("This is rectangle")
        //mengakses function/method di super class
        super.printName()
    }
}

open class Triangle : Shape() {
    override val corner: Int = 3
}