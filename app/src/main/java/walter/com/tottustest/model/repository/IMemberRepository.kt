package walter.com.tottustest.model.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import walter.com.tottustest.infraestructure.model.Result
import walter.com.tottustest.model.entity.Member

/**
 * Project name: TottusTest
 * Created by Walter Cojal on 11/5/20.
 */
interface IMemberRepository {

    fun insert(member: Member): Flow<Result<Long>>
    fun delete(member: Member): Flow<Result<Any>>
    fun getTeamMembers(teamId: Int): LiveData<List<Member>>

}