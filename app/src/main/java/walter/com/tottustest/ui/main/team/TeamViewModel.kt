package walter.com.tottustest.ui.main.team

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import walter.com.tottustest.domain.TeamInteractorImpl
import walter.com.tottustest.infraestructure.base.BaseViewModel
import walter.com.tottustest.infraestructure.model.ApiError
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Team
import walter.com.tottustest.model.repository.TeamRepositoryImpl
import walter.com.tottustest.model.room.AppDatabase
import walter.com.tottustest.util.liveData.ActivityEvent
import walter.com.tottustest.util.liveData.Event
import walter.com.tottustest.util.liveData.SingleEvent

class TeamViewModel(application: Application) : BaseViewModel(application) {

    private val teamsObservable = MutableLiveData<List<Team>>()
    val teamsObserver: LiveData<List<Team>> = teamsObservable

    private val dao = AppDatabase.newInstance(application.applicationContext).teamDao()
    private val repository = TeamRepositoryImpl(dao)
    private val interactor = TeamInteractorImpl(repository)

    fun getAllTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getList()
                .catch {
                    onMessageLiveData().postValue(it.localizedMessage)
                }
                .collect {
                    teamsObservable.postValue(it)
                }
        }
    }

    fun addTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.addTeam(team)
                .collect {
                    when (it) {
                        is Result.Success->getAllTeams()
                        is Result.Error->onMessageLiveData().postValue(it.error.message)
                    }
                }
        }
    }

}