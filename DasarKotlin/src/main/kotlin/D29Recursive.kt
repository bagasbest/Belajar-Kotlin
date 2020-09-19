
fun faktorial(n:Int):Int {
    return when (n) {
        1 -> 1
        else -> n*faktorial(n-1);
    }
}

tailrec fun display(n:Int) {
    if(n > 0) {
        println("Recursive Function $n")
        display(n -1)
    }
}



fun main() {
    var n:Int = 0
    
    tailrec fun displayFactorial (value:Int, total:Int=1) :Int {
        return when (value) {
            1 -> total
            else -> displayFactorial(value-1, total*value)
        }
    }

    println(displayFactorial(10))
}