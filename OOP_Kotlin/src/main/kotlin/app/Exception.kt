package app

import exception.ValidationException

fun validateAndSayHello(name: String) {
    if(name.isBlank()){
        throw ValidationException("Name is blank")
    }else{
        println("Hello $name")
    }
}

fun main() {
    try {
        validateAndSayHello("Bagas")
        validateAndSayHello("")
    } catch (error: ValidationException) {
        println("Terjadi error : ${error.message}")
    } catch (error: Throwable){
        println("Terjadi error ${error.message}")
    } finally {
        println("Program selesai")
    }
}