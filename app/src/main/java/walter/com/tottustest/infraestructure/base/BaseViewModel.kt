package walter.com.tottustest.infraestructure.base

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import walter.com.tottustest.infraestructure.model.ApiError
import walter.com.tottustest.util.liveData.ActivityEvent
import walter.com.tottustest.util.liveData.Event
import walter.com.tottustest.util.liveData.SingleEvent
import java.util.*

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */

open class BaseViewModel(protected val app: Application) : AndroidViewModel(app) {

    val context = app.applicationContext

    private val errorLiveData = SingleEvent<ApiError>()
    private val progressLiveData = SingleEvent<Boolean>()
    private val messageLiveData = SingleEvent<String>()
    private val intentLiveData = SingleEvent<Event<ActivityEvent>>()

    fun onErrorLiveData() = errorLiveData
    fun onProgressLiveData() = progressLiveData
    fun onMessageLiveData() = messageLiveData
    fun onIntentLiveData() = intentLiveData

    open fun manageError(error: ApiError) {
        Log.e("service error", "${error.message}, ${error.code}")
        onProgressLiveData().postValue(false)
        onErrorLiveData().postValue(error)
    }

}