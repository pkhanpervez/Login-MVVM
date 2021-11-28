package com.parveztest.Login.MVVM.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.parveztest.Login.MVVM.model.LoginModel
import com.parveztest.Login.MVVM.model.LoginRequest
import com.parveztest.Login.R

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {

    lateinit var progressBar: ProgressBar
    lateinit var button: Button
    lateinit var loginViewModel: LoginViewModel;
    lateinit var loginRequest: LoginRequest;
    lateinit var txtEmail: EditText;
    lateinit var txtPassword: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializer()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        observeResponse()

    }

    private fun initializer() {
        progressBar = findViewById(R.id.progressbar)
        button = findViewById(R.id.button)

        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)

        button.setOnClickListener(this)
    }

    private fun observeResponse() {
        loginViewModel.getLoginResponse().observe(this, Observer { loginResponse ->
            val stat = loginResponse.status
            if(stat.equals("success")) {
                Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
            }

            else if(stat.equals("failure")) {
                val msg = loginResponse.message
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        button.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        button.visibility = View.VISIBLE
    }

    override fun setError(message: String) {

    }

    override fun onClick(v: View?) {
        val email = txtEmail.text.toString().trim()
        val pwd = txtPassword.text.toString().trim()
        if(email.equals(""))
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show()
        else if(pwd.equals(""))
            Toast.makeText(this,"Please password",Toast.LENGTH_LONG).show()
        else {
            loginRequest = LoginRequest(email,pwd)
            loginViewModel.checkLogin(loginRequest, this)
        }

    }
}