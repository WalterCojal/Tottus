package walter.com.tottustest.model.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import walter.com.tottustest.infraestructure.model.ApiError
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.dao.TeamDao
import walter.com.tottustest.model.entity.Team
import java.lang.Exception

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class TeamRepositoryImpl (private val dao: TeamDao): ITeamRepository {

    override fun getAllList(): LiveData<List<Team>> = dao.getAllList()

    override fun addTeam(team: Team) = flow {
        try {
            dao.insert(team).apply {
                emit(Result.Success(this))
            }
        } catch (e: Exception) {
            emit(Result.Error(ApiError(0, e.localizedMessage ?: "")))
        }
    }

}