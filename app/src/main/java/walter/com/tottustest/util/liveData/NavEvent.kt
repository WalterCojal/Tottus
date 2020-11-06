package walter.com.tottustest.util.liveData

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Project name: cmachuancayo-android
 * Created by Walter Cojal on 10/23/20.
 */

abstract class NavEvent(val clazz: Class<*>, val bundle: Bundle?)

class ActivityEvent(clazz: Class<*>, bundle: Bundle? = null): NavEvent(clazz, bundle) {

    /**
    Utility that takes the class and bundle and generates an Intent to be consumed.
     */

    fun buildIntent(activity: AppCompatActivity): Intent = Intent(activity, clazz).apply {
        bundle?.let {
            putExtras(it)
        }
    }

}

class FragmentEvent(clazz: Class<*>, bundle: Bundle?, val tag: String = clazz.simpleName): NavEvent(clazz, bundle) {

    /**
    Create a fragment from the Fragment class via reflection and apply the Bundle as args.
     */
    fun buildFragment(): Fragment = (clazz.getConstructor().newInstance() as Fragment).apply {
        bundle?.let {
            this.arguments = it
        }
    }

}