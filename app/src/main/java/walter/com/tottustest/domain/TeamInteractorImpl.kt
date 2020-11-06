package walter.com.tottustest.domain

import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Team
import walter.com.tottustest.model.repository.ITeamRepository

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */

class TeamInteractorImpl(private val repository: ITeamRepository): ITeamInteractor {

    override fun getList(): Flow<List<Team>> = repository.getAllList().asFlow()
    override fun addTeam(team: Team): Flow<Result<Long>> = repository.addTeam(team)

}