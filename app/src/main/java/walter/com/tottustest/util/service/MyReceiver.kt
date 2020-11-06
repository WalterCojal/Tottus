package walter.com.tottustest.util.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import walter.com.tottustest.model.entity.User
import walter.com.tottustest.ui.main.HomeActivity

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.e("reciver", "restart")
        val user = intent.getParcelableExtra<User>("user")
        val _intent = Intent(context, HomeActivity::class.java).apply {
            putExtra("user", user)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(_intent)
    }
}
