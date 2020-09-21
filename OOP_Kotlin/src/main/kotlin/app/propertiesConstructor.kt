package app

import data.User

fun main() {
    val user1 = User("bagas", "rahasia123")

    println(user1.usernameParam)
    println(user1.passwordParam)

    user1.usernameParam = "bagas_best"
    user1.passwordParam = "rahasia"

    println(user1.usernameParam)
    println(user1.passwordParam)
}