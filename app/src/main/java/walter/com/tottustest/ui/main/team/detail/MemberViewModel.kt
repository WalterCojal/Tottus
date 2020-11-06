package walter.com.tottustest.ui.main.team.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import walter.com.tottustest.domain.MemberInteractorImpl
import walter.com.tottustest.infraestructure.base.BaseViewModel
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Member
import walter.com.tottustest.model.repository.MemberRepositoryImpl
import walter.com.tottustest.model.room.AppDatabase

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class MemberViewModel(application: Application): BaseViewModel(application) {

    private val dao = AppDatabase.newInstance(app.applicationContext).memberDao()
    private val repository = MemberRepositoryImpl(dao)
    private val interactor = MemberInteractorImpl(repository)

    private val membersObservable = MutableLiveData<List<Member>>()
    val membersObserver: LiveData<List<Member>> = membersObservable

    fun getMembers(teamId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getTeamMembers(teamId)
                .onStart { onProgressLiveData().postValue(true) }
                .catch {
                    onProgressLiveData().postValue(false)
                    onMessageLiveData().postValue(it.localizedMessage)
                }
                .collect {
                    membersObservable.postValue(it)
                }
        }
    }

    fun addMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.insert(member)
                .catch {
                    onProgressLiveData().postValue(false)
                    onMessageLiveData().postValue(it.localizedMessage)
                }
                .collect {
                    when (it) {
                        is Result.Success->{
                            onMessageLiveData().postValue("Registro exitoso")
                            getMembers(member.team_id)
                        }
                        is Result.Error->onMessageLiveData().postValue(it.error.message)
                    }
                }
        }
    }

    fun delete(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.delete(member)
                .onStart { onProgressLiveData().postValue(true) }
                .catch {
                    onProgressLiveData().postValue(false)
                    onMessageLiveData().postValue(it.localizedMessage)
                }
                .collect {
                    when (it) {
                        is Result.Success->getMembers(member.team_id)
                        is Result.Error->onMessageLiveData().postValue(it.error.message)
                    }
                    onProgressLiveData().postValue(false)
                }
        }
    }

}