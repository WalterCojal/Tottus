package walter.com.tottustest.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import walter.com.tottustest.R
import walter.com.tottustest.ui.auth.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }

    }
}