package walter.com.tottustest.domain

import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Member
import walter.com.tottustest.model.repository.IMemberRepository

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
class MemberInteractorImpl (private val repository: IMemberRepository): IMemberInteractor {

    override fun insert(member: Member): Flow<Result<Long>> = repository.insert(member)
    override fun delete(member: Member): Flow<Result<Any>> = repository.delete(member)
    override fun getTeamMembers(teamId: Int): Flow<List<Member>> = repository.getTeamMembers(teamId).asFlow()

}