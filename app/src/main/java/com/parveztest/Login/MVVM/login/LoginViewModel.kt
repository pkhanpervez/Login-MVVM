package com.parveztest.Login.MVVM.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parveztest.Login.MVVM.model.LoginModel
import com.parveztest.Login.MVVM.retrofit.IAppAPIs
import com.parveztest.Login.MVVM.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginResponse = MutableLiveData<LoginModel>()
    private val userService: IAppAPIs = RetrofitClient.retrofitInstance.create(
        IAppAPIs::class.java)

    private var compositeDisposable = CompositeDisposable()

    fun checkLogin(loginModel: LoginModel, userview: LoginView) {
        compositeDisposable.add(
            userService.getLogin(loginModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({
                    userview.showProgress()
                })
                .doOnTerminate({
                    userview.hideProgress()
                })
                .subscribe(
                    { loginRes ->
                        loginResponse.value = loginRes
                    },
                    { throwable ->
                        Log.e("LoginActivity", throwable.message ?: "onError")
                    }
                )
        )
    }
}