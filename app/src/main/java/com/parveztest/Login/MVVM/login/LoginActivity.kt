package com.parveztest.Login.MVVM.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.parveztest.Login.R

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {

    lateinit var progressBar: ProgressBar
    lateinit var button: Button
    lateinit var loginViewModel: LoginViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializer()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun initializer() {
        progressBar = findViewById(R.id.progressbar)
        button = findViewById(R.id.button)

        button.setOnClickListener(this)
    }

//    private fun observeData() {
//        loginViewModel.getUsers().observe(this, Observer { users ->
//
//        })
//    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setError(message: String) {

    }

    override fun onClick(v: View?) {
        Log.d("click", "login")
    }
}