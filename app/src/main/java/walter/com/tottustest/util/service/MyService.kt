package walter.com.tottustest.util.service

import android.app.IntentService
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import walter.com.tottustest.model.entity.User
import walter.com.tottustest.ui.main.HomeActivity

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class MyService: IntentService(MyService::class.simpleName) {

    private var user: User? = null

    private val counter: CountDownTimer = object: CountDownTimer(5 * 1000, 1000) {
        override fun onFinish() {
            Log.e("timer", "Mandar broadcast")
            /*val receiver = MyReceiver()
            receiver.onReceive(applicationContext, Intent().apply {
                action = "action.restart"
                putExtra("user", user)
            })*/
            val intent2 = Intent(baseContext, HomeActivity::class.java)
            intent2.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent2.putExtra("user", user)
            application.startActivity(intent2)
            onDestroy()
        }
        override fun onTick(millisUntilFinished: Long) {
            Log.e("timer", millisUntilFinished.toString())
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        user = intent?.getParcelableExtra("user")
        counter.start()
    }

}