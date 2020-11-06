package walter.com.tottustest.ui.auth

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import walter.com.tottustest.domain.AuthInteractorImpl
import walter.com.tottustest.infraestructure.base.BaseViewModel
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.User
import walter.com.tottustest.model.repository.UserRepositoryImpl
import walter.com.tottustest.model.room.AppDatabase
import walter.com.tottustest.ui.auth.login.LoginActivity
import walter.com.tottustest.ui.main.HomeActivity
import walter.com.tottustest.util.liveData.ActivityEvent
import walter.com.tottustest.util.liveData.Event
import walter.com.tottustest.util.toMD5

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class AuthViewModel(application: Application): BaseViewModel(application) {

    private val authDao = AppDatabase.newInstance(application.applicationContext).userDao()
    private val repository = UserRepositoryImpl(authDao)
    private val interactor = AuthInteractorImpl(repository)

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.findByEmail(email)
                .onStart { onProgressLiveData().postValue(true) }
                .catch {
                    onProgressLiveData().postValue(false)
                    onMessageLiveData().postValue("No se encuentra registrado")
                }
                .collect {
                    it?.let {
                        if (it.password == password.toMD5()) {
                            val bundle = Bundle().apply {
                                putParcelable("user", it)
                            }
                            onIntentLiveData().postValue(Event(ActivityEvent(HomeActivity::class.java, bundle)))
                        } else {
                            onProgressLiveData().postValue(false)
                            onMessageLiveData().postValue("No se encuentra registrado")
                        }
                    } ?: kotlin.run {
                        onProgressLiveData().postValue(false)
                        onMessageLiveData().postValue("No se encuentra registrado")
                    }
                }
        }
    }

    fun register(user: User) {
        user.password = user.password.toMD5()
        viewModelScope.launch(Dispatchers.IO) {
            interactor.register(user)
                .onStart { onProgressLiveData().postValue(true) }
                .catch { onMessageLiveData().postValue(it.localizedMessage) }
                .collect {
                    when (it) {
                        is Result.Success->{
                            onMessageLiveData().postValue("Usuario registrado")
                            onIntentLiveData().postValue(Event(ActivityEvent(LoginActivity::class.java)))
                        }
                        is Result.Error->{
                            onProgressLiveData().postValue(false)
                            onMessageLiveData().postValue(it.error.message)
                        }
                    }
                }
        }

    }

}