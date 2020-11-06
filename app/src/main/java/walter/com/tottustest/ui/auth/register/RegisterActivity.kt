package walter.com.tottustest.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_register.*
import walter.com.tottustest.R
import walter.com.tottustest.model.entity.User
import walter.com.tottustest.ui.auth.AuthViewModel
import walter.com.tottustest.util.isEmailValid
import walter.com.tottustest.util.liveData.EventObserver

class RegisterActivity : AppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        btnSignup.setOnClickListener {
            register()
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

    private fun register() {
        if (txtName.text?.toString()?.isEmpty() == true) {
            txtName.error = "Este campo es necesario"
            return
        }
        if (txtLastname.text?.toString()?.isEmpty() == true) {
            txtLastname.error = "Este campo es necesario"
            return
        }
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

        val user = User(
            0,
            txtName.text?.toString() ?: "",
            txtLastname.text?.toString() ?: "",
            txtEmail.text?.toString() ?: "",
            txtPassword.text?.toString() ?: ""
        )

        viewModel.register(user)
    }

}