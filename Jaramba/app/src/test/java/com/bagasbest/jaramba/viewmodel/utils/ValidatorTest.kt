package com.bagasbest.jaramba.viewmodel.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {


    @Test
    fun userValidLogin() {
        val email = "example@gmail.com"
        val password = "somePassword"
        val result = Validator.validateLogin(email, password)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userInvalidLogin() {
        val email = "examplegmailcom"
        val password = ""
        val result = Validator.validateLogin(email, password)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun userValidRegister() {
        val email = "example@gmail.com"
        val password = "somePassword"
        val username = "bagasbest"
        val phone = "081234567890"
        val result = Validator.validateRegister(email, password, username, phone)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userInvalidRegister() {
        val email = "examplegmailcom"
        val password = ""
        val username = ""
        val phone = ""
        val result = Validator.validateRegister(email, password, username, phone)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun userValidForgotPassword() {
        val email = "example@gmail.com"
        val result = Validator.validateForgotPassword(email)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userInvalidForgotPassword() {
        val email = "examplegmailcom"
        val result = Validator.validateForgotPassword(email)
        assertThat(result).isEqualTo(false)
    }


    @Test
    fun userValidateStartTripScreen() {
        val currentLocation = "Cicaheum"
        val destination  = "Cibureum"
        val trayek = "Cicaheum - Cibureum"
        val result = Validator.validateStartTripScreen(currentLocation, destination, trayek)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userInvalidateStartTripScreen() {
        val currentLocation = ""
        val destination  = ""
        val trayek = ""
        val result = Validator.validateStartTripScreen(currentLocation, destination, trayek)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun userValidateStartTripDetail() {
        val totalPassanger = 1
        val price = 3000
        val paymentMethod = "Cash"
        val result = Validator.validateStartTripDetail(totalPassanger, price, paymentMethod)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userInvalidateStartTripDetail() {
        val paymentMethod = ""
        val result = Validator.validateStartTripDetail(0, 0, paymentMethod)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun userValidateFinishTrip() {
        val rating = 5.0
        val review = "Aplikasi Jaramba Mobile Sangat Membantu Saya Dalam Kehidupan Sehari - hari"
        val result = Validator.validateFinishTrip(rating, review)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userInvalidateFinishTrip() {
        val rating = 0.0
        val review = ""
        val result = Validator.validateFinishTrip(rating, review)
        assertThat(result).isEqualTo(false)
    }

}