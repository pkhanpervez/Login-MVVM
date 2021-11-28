package com.parveztest.Login.MVVM.model

data class LoginModel (
    var status: String,
    var message: String
)

data class LoginRequest (
    var email: String,
    var pwd: String
        )