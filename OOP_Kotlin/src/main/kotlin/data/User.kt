package data

class User (var usernameParam: String, var passwordParam: String) {
    override fun toString(): String {
        return "User with username=${usernameParam}"
    }
}