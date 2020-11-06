package walter.com.tottustest.model.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Team

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
interface ITeamRepository {

    fun getAllList(): LiveData<List<Team>>
    fun addTeam(team: Team): Flow<Result<Long>>

}