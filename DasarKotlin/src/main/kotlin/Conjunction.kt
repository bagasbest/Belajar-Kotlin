
fun main () {
    val officeOpen = 7
    val officeClose = 20
    val now = 20

    val isOpen = if (now>= officeOpen && now<= officeClose) {
        true
    }else {
        false
    }

    println("Office is open  : $isOpen")



}