fun main() {
    var counter: Int = 0

    val lambdaIncrement: () -> Unit = {
        println("Lambda increment")
        counter++
    }

    val anonymousFun = fun() {
        println("Anonymous Function increment")
        counter++
    }

    fun functionIncrement() {
        println("Function Increment")
        counter++
    }

    lambdaIncrement()
    anonymousFun()
    functionIncrement()
    lambdaIncrement()
}