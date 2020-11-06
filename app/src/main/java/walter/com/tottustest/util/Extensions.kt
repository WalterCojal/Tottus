package walter.com.tottustest.util

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import walter.com.tottustest.R
import java.security.MessageDigest
import java.util.*

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */

fun Toolbar.addNavIcon(context: Context) {
    navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
    setTitleTextColor(Color.WHITE)
    setNavigationOnClickListener { (context as AppCompatActivity).onBackPressed() }
}

inline fun <T: Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }

fun String.isEmailValid(): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex().toUpperCase(Locale.ROOT)
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}
