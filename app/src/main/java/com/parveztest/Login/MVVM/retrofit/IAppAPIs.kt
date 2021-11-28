package com.parveztest.Login.MVVM.retrofit

import com.parveztest.Login.MVVM.model.LoginModel
import com.parveztest.Login.MVVM.model.LoginRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IAppAPIs {

    @POST("login/")
    fun getLogin(@Body login: LoginRequest) : Observable<LoginModel>
}