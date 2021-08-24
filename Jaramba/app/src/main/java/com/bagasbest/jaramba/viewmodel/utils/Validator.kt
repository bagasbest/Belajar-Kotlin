package com.bagasbest.jaramba.viewmodel.utils

object Validator {

    fun validateLogin(email: String, password: String) : Boolean {
        return !(email.isEmpty() || !email.contains("@") || !email.contains(".") || password.isEmpty())
    }

    fun validateRegister(email: String, password: String, username: String, phone: String) : Boolean{
        return !(email.isEmpty() || !email.contains("@") || !email.contains(".") || password.isEmpty() || username.isEmpty() || phone.isEmpty())
    }


    fun validateForgotPassword(email: String) : Boolean {
        return !(email.isEmpty() || !email.contains("@") || !email.contains("."))
    }

    fun validateStartTripScreen(currentLocation: String, destination: String, trayek: String) : Boolean {
        return !(currentLocation.isEmpty() || destination.isEmpty() || trayek.isEmpty())
    }

    fun validateStartTripDetail(totalPassanger: Int, price: Int, paymentMethod: String): Boolean{
        return !(totalPassanger == 0 || price == 0 || paymentMethod.isEmpty())
    }

    fun validateFinishTrip(rating: Double, review: String) : Boolean {
        return !(rating == 0.0 || review.isEmpty())
    }
}