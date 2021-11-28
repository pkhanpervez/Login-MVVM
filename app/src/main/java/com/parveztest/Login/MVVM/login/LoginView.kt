package com.parveztest.Login.MVVM.login

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setError(message: String)
}