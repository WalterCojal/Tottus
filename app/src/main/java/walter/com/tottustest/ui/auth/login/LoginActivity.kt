package walter.com.tottustest.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import walter.com.tottustest.R
import walter.com.tottustest.ui.auth.AuthViewModel
import walter.com.tottustest.ui.auth.register.RegisterActivity
import walter.com.tottustest.util.isEmailValid
import walter.com.tottustest.util.liveData.EventObserver

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        btnSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            login()
        }

        viewModel.onMessageLiveData().observe(this, Observer {
            showMessage(it)
        })

        viewModel.onIntentLiveData().observe(this, EventObserver {
            startActivity(it.buildIntent(this))
            finish()
        })

    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun login() {
        if (txtEmail.text?.toString()?.isEmpty() == true) {
            txtEmail.error = "Este campo es necesario"
            return
        }
        if (txtEmail.text?.toString()?.isEmailValid() == false) {
            txtEmail.error = "Correo inválido"
            return
        }
        if (txtPassword.text?.toString()?.isEmpty() == true) {
            txtPassword.error = "Este campo es necesario"
            return
        }

        viewModel.login(txtEmail.text?.toString() ?: "", txtPassword.text?.toString() ?: "")
    }

}